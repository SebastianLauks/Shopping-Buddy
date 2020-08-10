package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingListsRepository

class ShoppingListsViewModel(private val shoppingListsRepository: ShoppingListsRepository): ViewModel() {

    fun getShoppingLists() = shoppingListsRepository.getShoppingLists()


}