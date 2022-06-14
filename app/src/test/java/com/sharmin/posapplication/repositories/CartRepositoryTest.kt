package com.sharmin.posapplication.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.ext.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class CartRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository = CartRepository()

    @Before
    fun setUp() {
        val product = Product("Product 1", ProductType.DRINKS, 100, 10, "img1").apply {
            id = 1
        }
        repository.clearCart()
        repository.addItemToCart(product, 1)
    }

    @Test
    fun `addItemToCart Given cart does not contain the product Then cart size increases by one and price gets updated`() {
        val product2 = Product("Product 2", ProductType.ICE_CREAMS, 50, 10, "img2").apply {
            id = 2
        }

        repository.addItemToCart(product2, 2)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 2)
        assertEquals(cartItems[1].product.name, "Product 2")
        assertEquals(cartTotal, 200)
    }

    @Test
    fun `addItemToCart Given cart contains the product Then cart size stays the same and price and quantity gets updated`() {
        val product = Product("Product 1", ProductType.DRINKS, 100, 10, "img1").apply {
            id = 1
        }

        repository.addItemToCart(product, 2)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 1)
        assertEquals(cartItems[0].quantity, 3)
        assertEquals(cartItems[0].product.name, "Product 1")
        assertEquals(cartTotal, 300)
    }

    @Test
    fun `reduceItemFromCart Given cart contains single number of product and quantity is reduced by 1 Then cart gets cleared`() {
        val product = Product("Product 1", ProductType.DRINKS, 100, 10, "img1").apply {
            id = 1
        }

        repository.reduceItemFromCart(product, 1)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 0)
        assertEquals(cartTotal, 0)
    }

    @Test
    fun `reduceItemFromCart Given quantity is reduced by an amount more than the available amount Then cart gets cleared`() {
        val product = Product("Product 1", ProductType.DRINKS, 100, 10, "img1").apply {
            id = 1
        }

        repository.reduceItemFromCart(product, 3)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 0)
        assertEquals(cartTotal, 0)
    }

    @Test
    fun `reduceItemFromCart Given quantity is reduced by an amount less than the available amount Then cart gets updated correctly`() {
        val product = Product("Product 1", ProductType.DRINKS, 100, 10, "img1").apply {
            id = 1
        }
        repository.addItemToCart(product, 3)

        repository.reduceItemFromCart(product, 2)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 1)
        assertEquals(cartItems[0].quantity, 2)
        assertEquals(cartItems[0].product.name, "Product 1")
        assertEquals(cartTotal, 200)
    }

    @Test
    fun `reduceItemFromCart Given item to reduce is not in the cart Then cart remains same`() {
        val product2 = Product("Product 2", ProductType.DRINKS, 50, 10, "img2").apply {
            id = 2
        }

        repository.reduceItemFromCart(product2, 2)
        val cartItems = repository.cartItemsLiveData.getOrAwaitValue()
        val cartTotal = repository.cartTotalLiveData.getOrAwaitValue()

        assertEquals(cartItems.size, 1)
        assertEquals(cartItems[0].quantity, 1)
        assertEquals(cartItems[0].product.name, "Product 1")
        assertEquals(cartTotal, 100)
    }
}