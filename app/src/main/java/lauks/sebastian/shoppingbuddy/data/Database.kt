package lauks.sebastian.shoppingbuddy.data

class Database private constructor(){

    val productDao = ProductDao()


    companion object {
        @Volatile private var instance: Database? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: Database().also { instance = it }
            }
    }
}