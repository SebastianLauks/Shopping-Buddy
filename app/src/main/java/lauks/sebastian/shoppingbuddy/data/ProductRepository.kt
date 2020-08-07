package lauks.sebastian.shoppingbuddy.data

class ProductRepository private constructor(private val productsInShoppingListDao: ProductsInShoppingListDao) {

    fun addProduct(product: Product){
        productsInShoppingListDao.addProduct(product)
    }

    fun getProductsToBuy() = productsInShoppingListDao.getProductsToBuy()

    fun getProductsInCart() = productsInShoppingListDao.getProductsInCart()

    fun removeProduct(product: Product) {
        productsInShoppingListDao.removeProduct(product)
    }

    fun moveProductsToCart(product: Product){
        productsInShoppingListDao.moveProductsToCart(product)
    }

    fun moveProductsFromCart(product: Product){
        productsInShoppingListDao.moveProductsFromCart(product)
    }

    companion object {
        @Volatile private var instance: ProductRepository? = null

        fun getInstance(productsInShoppingListDao: ProductsInShoppingListDao) =
            instance ?: synchronized(this){
                instance ?: ProductRepository(productsInShoppingListDao).also { instance = it }
            }
    }
}