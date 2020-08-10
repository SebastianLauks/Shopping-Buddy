package lauks.sebastian.shoppingbuddy.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lauks.sebastian.shoppingbuddy.data.products.ProductsRepository

class ProductsViewModelFactory(private val productsRepository: ProductsRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepository) as T
    }
}