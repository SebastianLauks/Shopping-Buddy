package lauks.sebastian.shoppingbuddy.data.shopping_lists

import androidx.lifecycle.LiveData
import lauks.sebastian.shoppingbuddy.data.products.Product

data class ShoppingList(var id:String="-1", var name: String, var code: String="-1") {

    init {
        if(code == "-1"){
            code = generateHash(6)
        }
    }

    private fun generateHash(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}