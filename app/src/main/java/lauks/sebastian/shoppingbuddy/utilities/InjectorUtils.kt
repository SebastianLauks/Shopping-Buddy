package lauks.sebastian.shoppingbuddy.utilities

import lauks.sebastian.shoppingbuddy.data.Database
import lauks.sebastian.shoppingbuddy.data.products.ProductsRepository
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingListsRepository
import lauks.sebastian.shoppingbuddy.ui.products.ProductsViewModelFactory
import lauks.sebastian.shoppingbuddy.ui.shopping_lists.ShoppingListsViewModelFactory

object InjectorUtils {

    fun provideProductsViewModelFactory(): ProductsViewModelFactory {
        val productRepository = ProductsRepository.getInstance(Database.getInstance().productDao)
        return ProductsViewModelFactory(productRepository)
    }

    fun provideShoppingListsViewModelFactory(): ShoppingListsViewModelFactory{
        val shoppingListsRepository = ShoppingListsRepository.getInstance(Database.getInstance().shoppingListsDao)
        return ShoppingListsViewModelFactory(shoppingListsRepository)
    }
}