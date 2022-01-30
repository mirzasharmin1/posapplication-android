package com.sharmin.posapplication.repositories

import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.domain.models.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor() {

    var cart = listOf<CartItem>()
    val cartItemsLiveData: MutableLiveData<List<CartItem>> = MutableLiveData(cart)

    fun addItemToCart(product: Product, quantity: Int) {
        cart.plus(CartItem(product, quantity))
        cartItemsLiveData.value = cart
    }

    fun reduceItemFromCart(product: Product, quantity: Int) {
        val cartItem = cart.find { it.product == product } ?: return
        cartItem.quantity -= quantity

        if (cartItem.quantity <= 0) {
            cart = cart.filter { it.product != product }
        }

        cartItemsLiveData.value = cart
    }
}