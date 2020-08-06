package lauks.sebastian.shoppingbuddy.data

data class Product (var id:String="-1", var name: String, var inCart: Boolean = false){

    override fun toString(): String {
        return name
    }
}