package com.sharmin.posapplication.domain.models

import com.sharmin.posapplication.db.models.Product
import java.text.DecimalFormat

class CartItem(val product: Product, var quantity: Int) {

    val totalPrice = product.price * quantity
}