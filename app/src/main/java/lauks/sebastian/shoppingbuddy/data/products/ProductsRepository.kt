package lauks.sebastian.shoppingbuddy.data.products

class ProductsRepository private constructor(private val productsDao: ProductsDao) {

    fun startListening(shoppingListKey: String) = productsDao.startListening(shoppingListKey)

    fun addProduct(product: Product){
        productsDao.addProduct(product)
    }

    fun getProductsToBuy() = productsDao.getProductsToBuy()

    fun getProductsInCart() = productsDao.getProductsInCart()

    fun removeProduct(product: Product) {
        productsDao.removeProduct(product)
    }

    fun moveProductsToCart(product: Product){
        productsDao.moveProductsToCart(product)
    }

    fun moveProductsToCartLocally(product: Product){
        productsDao.moveProductsToCartLocally(product)
    }

    fun moveProductsFromCart(product: Product){
        productsDao.moveProductsFromCart(product)
    }

    fun moveProductsFromCartLocally(product: Product){
        productsDao.moveProductsFromCartLocally(product)
    }

    fun removeProductsFromInCart(){
        productsDao.removeProductsFromInCart()
    }

    companion object {
        @Volatile private var instance: ProductsRepository? = null

        fun getInstance(productsDao: ProductsDao) =
            instance ?: synchronized(this){
                instance
                    ?: ProductsRepository(
                        productsDao
                    ).also { instance = it }
            }
    }
}