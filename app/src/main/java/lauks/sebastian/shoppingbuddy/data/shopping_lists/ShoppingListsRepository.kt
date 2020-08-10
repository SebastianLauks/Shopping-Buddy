package lauks.sebastian.shoppingbuddy.data.shopping_lists

class ShoppingListsRepository  private constructor(private val shoppingListsDao: ShoppingListsDao){

    fun getShoppingLists() = shoppingListsDao.getShoppingLists()


    companion object {
        @Volatile private var instance: ShoppingListsRepository? = null

        fun getInstance(shoppingLists: ShoppingListsDao) =
            instance ?: synchronized(this){
                instance
                    ?: ShoppingListsRepository(
                        shoppingLists
                    ).also { instance = it }
            }
    }
}