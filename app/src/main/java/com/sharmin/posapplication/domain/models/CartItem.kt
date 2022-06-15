package com.sharmin.posapplication.domain.models

import com.sharmin.posapplication.db.models.Product

class CartItem(val product: Product, val quantity: Int) {

    init {
        println("Created a cartItem with $product at $quantity quantity.")
    }

    val totalPrice
        get() = product.price * quantity
}