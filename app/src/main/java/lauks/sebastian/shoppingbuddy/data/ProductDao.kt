package lauks.sebastian.shoppingbuddy.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProductDao {
    private val shoppingListProductsInFB: DatabaseReference
    private val productsInFB: DatabaseReference
    private val allProducts = mutableListOf<Product>()
    private val productsList = mutableListOf<Product>()
    private val productsLiveData = MutableLiveData<List<Product>>()

    private var shoppingListKey = "shoppinglist1" //in the future it will be passed as an argument

    init {
        productsLiveData.value = productsList
        shoppingListProductsInFB = Firebase.database.reference.child("ShoppingLists").child(shoppingListKey).child("products")
        productsInFB = Firebase.database.reference.child("Products2")

        val productsListener = object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                productsList.clear()
                getProductsFromFB(snapshot)
            }

        }

        shoppingListProductsInFB.addValueEventListener(productsListener)
    }

    private fun getProductsFromFB(outsideSnapshot: DataSnapshot){
        productsInFB.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                allProducts.clear()
                snapshot.children.forEach { product:DataSnapshot ->
                    val productMap = product.value as HashMap<String, *>
                    allProducts.add(Product(productMap["id"]!!.toString(), productMap["name"]!!.toString()))
                }
                continueProductsRetrieving(outsideSnapshot)
            }

        })
    }

    private fun removeProductFromFB(product: Product){
        shoppingListProductsInFB.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
               snapshot.children.forEach { productRetrieved: DataSnapshot ->
                   val productMap = productRetrieved.value as HashMap<String, *>
                   if(product.name == productMap["name"].toString()){
                       shoppingListProductsInFB.child(productRetrieved.key!!).removeValue()
                   }
               }
            }

        })
    }
    fun continueProductsRetrieving(snapshot: DataSnapshot){
        snapshot.children.forEach{productRef: DataSnapshot ->
            val productReference = productRef.value as HashMap<String,*>
            Log.d("prodref", productReference["id"].toString())
            Log.d("siezeee",allProducts.size.toString())
            val productToAdd = allProducts.find{product ->
                product.id == productReference["id"].toString()
            }
            if(productToAdd != null){
                Log.d("niejenulem", "niejenulem")
                productToAdd.inCart = productReference["inCart"].toString().toBoolean()
                productsList.add(productToAdd)
            }
        }
        Log.d("tag", productsList.size.toString())
        productsLiveData.value = productsList
    }
    fun addProduct(product: Product){
//        productsList.add(product)
//        productsLiveData.value = productsList
        val pushedRef = shoppingListProductsInFB.push()
        val pushedId = pushedRef.key
        product.id = pushedId!!
        shoppingListProductsInFB.child(product.id).setValue(product)
    }

    fun removeProduct(product: Product){
//        shoppingListProductsInFB.child(product.id).removeValue()
        removeProductFromFB(product)
    }

    fun getProducts() = productsLiveData as LiveData<List<Product>>
}