package lauks.sebastian.shoppingbuddy.ui

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.data.ProductRepository

class ProductsViewModel(private val productRepository: ProductRepository): ViewModel() {

    fun getProducts() = productRepository.getProducts()

    fun addProduct(product: Product) = productRepository.addProduct(product)
}