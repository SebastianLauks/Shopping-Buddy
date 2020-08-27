package lauks.sebastian.shoppingbuddy.data.shopping_lists

class ShoppingListsRepository  private constructor(private val shoppingListsDao: ShoppingListsDao){

    fun getShoppingLists() = shoppingListsDao.getShoppingLists()

    fun removeShoppingList(shoppingList: ShoppingList) = shoppingListsDao.removeShoppingList(shoppingList)


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