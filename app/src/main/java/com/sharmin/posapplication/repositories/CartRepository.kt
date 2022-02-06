package com.sharmin.posapplication.repositories

import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.domain.models.CartItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {

    private var cart = listOf<CartItem>()
    val cartItemsLiveData: MutableLiveData<List<CartItem>> = MutableLiveData(cart)
    val cartTotalLiveData: MutableLiveData<Int> = MutableLiveData(0)

    fun addItemToCart(product: Product, quantity: Int) {
        val cartItemIdx = cart.indexOfFirst { it.product.id == product.id }

        cart = if (cartItemIdx != -1) {
            cart.mapIndexed { index, cartItem ->
                if (index == cartItemIdx)
                    CartItem(product, cartItem.quantity + quantity)
                else
                    cartItem
            }
        } else {
             cart.plus(CartItem(product, quantity))
        }

        cartItemsLiveData.value = cart
        cartTotalLiveData.value = findCartTotal()
    }

    fun reduceItemFromCart(product: Product, quantity: Int) {
        val cartItemIdx = cart.indexOfFirst { it.product == product }

        if (cartItemIdx == -1) return

        val cartItem = cart[cartItemIdx]

        cart = if (cartItem.quantity - quantity <= 0) {
            cart.filter { it.product != product }
        } else {
            cart.mapIndexed { index, currentCartItem ->
                if (index == cartItemIdx)
                    CartItem(product, cartItem.quantity - quantity)
                else
                    currentCartItem
            }
        }

        cartItemsLiveData.value = cart
        cartTotalLiveData.value = findCartTotal()
    }

    private fun findCartTotal(): Int {
        return cart.fold(0) { acc, currItem ->
            acc + currItem.totalPrice
        }
    }
}