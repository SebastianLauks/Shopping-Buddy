package lauks.sebastian.shoppingbuddy.utilities

import lauks.sebastian.shoppingbuddy.data.Database
import lauks.sebastian.shoppingbuddy.data.ProductRepository
import lauks.sebastian.shoppingbuddy.ui.products.ProductsViewModelFactory

object InjectorUtils {

    fun proviceProductsViewModelFactory(): ProductsViewModelFactory {
        val productRepository = ProductRepository.getInstance(Database.getInstance().productDao)
        return ProductsViewModelFactory(productRepository)

    }
}