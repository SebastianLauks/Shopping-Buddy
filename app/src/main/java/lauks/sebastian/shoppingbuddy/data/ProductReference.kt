package lauks.sebastian.shoppingbuddy.data

import com.google.firebase.database.Exclude

data class ProductReference(val map: Map<String,String>) {

    val id by map
    val inCart by map
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "inCart" to inCart
        )
    }
}