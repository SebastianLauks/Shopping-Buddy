package lauks.sebastian.shoppingbuddy.data

data class Product (val id: String,var name: String){
    override fun toString(): String {
        return name
    }
}