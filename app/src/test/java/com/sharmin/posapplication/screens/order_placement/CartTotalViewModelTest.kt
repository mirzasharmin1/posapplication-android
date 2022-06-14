package com.sharmin.posapplication.screens.order_placement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.ext.getOrAwaitValue
import com.sharmin.posapplication.repositories.CartRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CartTotalViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var  cartRepository: CartRepository
    private lateinit var viewModel: CartTotalViewModel

    private val cartTotal = 120

    @Before
    fun setUp() {
        val cartTotalLiveData = MutableLiveData<Int>().apply {
            value = cartTotal
        }
        cartRepository = mock()
        whenever(cartRepository.cartTotalLiveData).thenReturn(cartTotalLiveData)
        viewModel = CartTotalViewModel(cartRepository)
    }

    @Test
    fun `cartTotal return cartTotal returned by cartRepository`() {
        val cartTotalValue = viewModel.cartTotal.getOrAwaitValue()
        assertEquals(cartTotalValue, cartTotal)
    }
}