package lauks.sebastian.shoppingbuddy.ui.products

import androidx.lifecycle.ViewModel
import lauks.sebastian.shoppingbuddy.data.Product
import lauks.sebastian.shoppingbuddy.data.ProductRepository

class ProductsViewModel(private val productRepository: ProductRepository): ViewModel() {



    fun getProductsToBuy() = productRepository.getProductsToBuy()

    fun getProductsInCart() = productRepository.getProductsInCart()

    fun addProduct(product: Product) = productRepository.addProduct(product)

    fun removeProduct(product: Product) = productRepository.removeProduct(product)

    fun moveProductsToCart(product: Product) = productRepository.moveProductsToCart(product)

    fun moveProductsFromCart(product: Product) = productRepository.moveProductsFromCart(product)

    fun moveProductsToCartLocally(product: Product) = productRepository.moveProductsToCartLocally(product)

    fun moveProductsFromCartLocally(product: Product) = productRepository.moveProductsFromCartLocally(product)

    fun removeProductsFromInCart() = productRepository.removeProductsFromInCart()

    fun findProductToBuy(name: String): Product? {
        return getProductsToBuy().value!!.find { product -> product.name == name }
    }

    fun findProductInCart(name: String): Product? {
        return getProductsInCart().value!!.find { product -> product.name == name }
    }

}