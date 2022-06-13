package com.sharmin.posapplication.screens.order_placement

import com.sharmin.posapplication.repositories.CartRepository
import org.mockito.kotlin.mock

class CartTotalViewModelTest {

    private val cartRepository = mock<CartRepository>()
    private val viewModel = CartTotalViewModel(cartRepository)
}