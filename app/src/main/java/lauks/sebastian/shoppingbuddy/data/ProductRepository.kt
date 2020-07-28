package lauks.sebastian.shoppingbuddy.data

class ProductRepository private constructor(private val productDao: ProductDao) {

    fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    fun getProducts() = productDao.getProducts()

    companion object {
        @Volatile private var instance: ProductRepository? = null

        fun getInstance(productDao: ProductDao) =
            instance ?: synchronized(this){
                instance ?: ProductRepository(productDao).also { instance = it }
            }
    }
}