package lauks.sebastian.shoppingbuddy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lauks.sebastian.shoppingbuddy.data.ProductRepository

class ProductsViewModelFactory(private val productRepository: ProductRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productRepository) as T
    }
}