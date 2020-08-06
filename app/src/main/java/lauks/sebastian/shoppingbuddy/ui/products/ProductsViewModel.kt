package lauks.sebastian.shoppingbuddy.ui.products

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.data.ProductRepository

class ProductsViewModel(private val productRepository: ProductRepository): ViewModel() {



    fun getProducts() = productRepository.getProducts()

    fun addProduct(product: Product) = productRepository.addProduct(product)

    fun removeProduct(product: Product) = productRepository.removeProduct(product)

    fun findProductByName(name: String): Product? {
        return getProducts().value!!.find { product -> product.name.equals(name)}
    }
}