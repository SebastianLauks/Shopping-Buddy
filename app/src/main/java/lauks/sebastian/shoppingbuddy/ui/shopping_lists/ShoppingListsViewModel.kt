package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingList
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingListsRepository

class ShoppingListsViewModel(private val shoppingListsRepository: ShoppingListsRepository): ViewModel() {

    fun getShoppingLists() = shoppingListsRepository.getShoppingLists()

    fun findShoppingList(name: String): ShoppingList?{
        return getShoppingLists().value?.find { shoppingList -> shoppingList.name == name }
    }

    fun removeShoppingList(shoppingList: ShoppingList) = shoppingListsRepository.removeShoppingList(shoppingList)

    fun createShoppingList(name: String) = shoppingListsRepository.createShoppingList(name)

    fun importShoppingList(code: String) = shoppingListsRepository.importShoppingList(code)

    fun startListening(userId:String) = shoppingListsRepository.startListening(userId)

}