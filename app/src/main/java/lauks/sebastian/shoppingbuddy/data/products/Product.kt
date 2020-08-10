package lauks.sebastian.shoppingbuddy.data.products

data class Product (var id:String="-1", var name: String, var inCart: Boolean = false){

    override fun toString(): String {
        return name
    }
}