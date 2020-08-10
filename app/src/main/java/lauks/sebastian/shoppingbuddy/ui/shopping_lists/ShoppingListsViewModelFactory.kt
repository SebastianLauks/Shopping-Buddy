package lauks.sebastian.shoppingbuddy.ui.shopping_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lauks.sebastian.shoppingbuddy.data.shopping_lists.ShoppingListsRepository

class ShoppingListsViewModelFactory (private val shoppingListsRepository: ShoppingListsRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingListsViewModel(shoppingListsRepository) as T
    }
}