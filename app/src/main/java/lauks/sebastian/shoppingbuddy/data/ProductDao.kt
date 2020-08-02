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
    private lateinit var database: DatabaseReference
    private val productList = mutableListOf<Product>()
    private val products = MutableLiveData<List<Product>>()

    init {
        products.value = productList
        database = Firebase.database.reference.child("Products")

        val productsListener = object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                snapshot.children.forEach{product: DataSnapshot ->
                    val productMap = product.value as HashMap<String,*>
                    productList.add(Product(product.key as String, productMap.get("name") as String))
                }
                products.value = productList
            }

        }

        database.addValueEventListener(productsListener)
    }

    fun addProduct(product: Product){
//        productList.add(product)
//        products.value = productList
        database.child(product.id).setValue(product)
    }

    fun removeProduct(product: Product){
        database.child(product.id).removeValue()
    }

    fun getProducts() = products as LiveData<List<Product>>
}