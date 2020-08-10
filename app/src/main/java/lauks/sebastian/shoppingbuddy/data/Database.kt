package lauks.sebastian.shoppingbuddy.data

import lauks.sebastian.shoppingbuddy.data.products.ProductsDao
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingListsDao

class Database private constructor(){

    val productDao = ProductsDao()
    val shoppingListsDao = ShoppingListsDao()


    companion object {
        @Volatile private var instance: Database? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: Database().also { instance = it }
            }
    }
}