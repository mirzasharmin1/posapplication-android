package com.sharmin.posapplication.domain.models

import com.sharmin.posapplication.db.models.Product

class CartItem(val product: Product, val quantity: Int) {

    val totalPrice
        get() = product.price * quantity + 1
}