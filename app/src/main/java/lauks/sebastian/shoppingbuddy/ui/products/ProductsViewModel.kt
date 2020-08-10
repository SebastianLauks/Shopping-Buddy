package lauks.sebastian.shoppingbuddy.ui.products

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.products.Product
import lauks.sebastian.shoppingbuddy.data.products.ProductsRepository

class ProductsViewModel(private val productsRepository: ProductsRepository): ViewModel() {

    fun startListening(shoppingListKey: String) = productsRepository.startListening(shoppingListKey)

    fun getProductsToBuy() = productsRepository.getProductsToBuy()

    fun getProductsInCart() = productsRepository.getProductsInCart()

    fun addProduct(product: Product) = productsRepository.addProduct(product)

    fun removeProduct(product: Product) = productsRepository.removeProduct(product)

    fun moveProductsToCart(product: Product) = productsRepository.moveProductsToCart(product)

    fun moveProductsFromCart(product: Product) = productsRepository.moveProductsFromCart(product)

    fun moveProductsToCartLocally(product: Product) = productsRepository.moveProductsToCartLocally(product)

    fun moveProductsFromCartLocally(product: Product) = productsRepository.moveProductsFromCartLocally(product)

    fun removeProductsFromInCart() = productsRepository.removeProductsFromInCart()

    fun findProductToBuy(name: String): Product? {
        return getProductsToBuy().value!!.find { product -> product.name == name }
    }

    fun findProductInCart(name: String): Product? {
        return getProductsInCart().value!!.find { product -> product.name == name }
    }

}