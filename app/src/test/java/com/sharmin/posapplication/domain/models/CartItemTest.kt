package com.sharmin.posapplication.domain.models

import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import org.junit.Test
import kotlin.test.assertEquals

class CartItemTest {

    @Test
    fun `totalPrice computed field contains proper price`() {
        val unitPrice = 2000        // In cents
        val units = 10
        val product = Product("Vanilla Ice Cream", ProductType.ICE_CREAMS, unitPrice, 100, "ice-cream.jpg")
        val cartItem = CartItem(product, units)

        val totalPrice = cartItem.totalPrice

        assertEquals(totalPrice, 20000)
    }
}