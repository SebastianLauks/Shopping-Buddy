package lauks.sebastian.shoppingbuddy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductDao {
    private val productList = mutableListOf<Product>()
    private val products = MutableLiveData<List<Product>>()

    init {
        products.value = productList
    }

    fun addProduct(product: Product){
        productList.add(product)
        products.value = productList
    }

    fun getProducts() = products as LiveData<List<Product>>
}