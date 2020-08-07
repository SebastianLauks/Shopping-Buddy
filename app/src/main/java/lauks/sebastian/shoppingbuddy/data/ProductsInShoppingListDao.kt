package lauks.sebastian.shoppingbuddy.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductsInShoppingListDao {
    private val shoppingListProductsInFB: DatabaseReference
    private val productsToBuyList = mutableListOf<Product>()
    private val productsToBuyLiveData = MutableLiveData<List<Product>>()
    private val productsInCartList = mutableListOf<Product>()
    private val productsInCartLiveData = MutableLiveData<List<Product>>()

    private var shoppingListKey = "shoppinglist1" //in the future it will be passed as an argument

    init {
        productsToBuyLiveData.value = productsToBuyList
        productsInCartLiveData.value = productsInCartList
        shoppingListProductsInFB =
            Firebase.database.reference.child("ShoppingLists").child(shoppingListKey)
                .child("products")

        shoppingListProductsInFB.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                productsToBuyList.clear()
                productsInCartList.clear()
                snapshot.children.forEach { productFB: DataSnapshot ->
                    @Suppress("UNCHECKED_CAST")
                    val productFromFB = productFB.value as HashMap<String, *>
                    val product = Product(
                        productFromFB["id"].toString(),
                        productFromFB["name"].toString(),
                        productFromFB["inCart"].toString().toBoolean()
                    )
                    if(product.inCart){
                        productsInCartList.add(product)
                    }
                    else{
                        productsToBuyList.add(product)
                    }
                }
                productsInCartLiveData.value = productsInCartList
                productsToBuyLiveData.value = productsToBuyList
            }
        })
    }

    fun removeProductsFromInCart(){
        productsInCartList.filter { it.inCart }.forEach { removeProduct(it) }
    }

    fun addProduct(product: Product) {
        shoppingListProductsInFB.orderByChild("name").equalTo(product.name)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null) {
                        val pushedRef = shoppingListProductsInFB.push()
                        val pushedId = pushedRef.key
                        product.id = pushedId!!
                        shoppingListProductsInFB.child(product.id).setValue(product)
                    }
                }

            })
    }

    fun removeProduct(product: Product) {
        shoppingListProductsInFB.orderByChild("name").equalTo(product.name)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(products: DataSnapshot) {
                    val productToRemove = products.value as HashMap<*, *>
                    shoppingListProductsInFB.child( productToRemove.keys.first().toString()).removeValue()
                }

            })
    }
    fun moveProductsToCartLocally(product: Product){
        productsToBuyList.remove(product)
        productsToBuyLiveData.value = productsToBuyList
    }

    fun moveProductsToCart(product: Product){
        product.inCart = true
        shoppingListProductsInFB.child(product.id).setValue(product)
    }

    fun moveProductsFromCartLocally(product: Product){
        productsInCartList.remove(product)
        productsInCartLiveData.value = productsInCartList
    }

    fun moveProductsFromCart(product: Product){
        productsToBuyList.remove(product)
        productsToBuyLiveData.value = productsToBuyList
        product.inCart = false
        shoppingListProductsInFB.child(product.id).setValue(product)
    }

    fun getProductsToBuy() = productsToBuyLiveData as LiveData<List<Product>>

    fun getProductsInCart() = productsInCartLiveData as LiveData<List<Product>>
}