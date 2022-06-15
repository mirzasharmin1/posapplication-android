package com.sharmin.posapplication.screens.order_placement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.domain.models.CartItem
import com.sharmin.posapplication.ext.getOrAwaitValue
import com.sharmin.posapplication.repositories.CartRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CartListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var  cartRepository: CartRepository
    private lateinit var viewModel: CartListViewModel

    private val cartItems = listOf<CartItem>(
        CartItem(
            Product("Product One", ProductType.ICE_CREAMS, 100, 10, "product1img.jpg").apply { id = 1 },
            1
        ),
        CartItem(
            Product("Product Two", ProductType.DRINKS, 50, 5, "product2img.jpg").apply { id = 2 },
            2
        )
    )

    @Before
    fun setUp() {
        val cartItemsLiveData = MutableLiveData<List<CartItem>>().apply {
            value = cartItems
        }
        cartRepository = mock()
        whenever(cartRepository.cartItemsLiveData).thenReturn(cartItemsLiveData)
        viewModel = CartListViewModel(cartRepository)
    }

    @Test
    fun `cartItems return cartItems returned by cartRepository`() {
        val cartItemsValue = viewModel.cartItems.getOrAwaitValue()

        assertEquals(cartItemsValue, cartItems)
    }
}