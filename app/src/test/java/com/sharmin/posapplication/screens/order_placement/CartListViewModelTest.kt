package com.sharmin.posapplication.screens.order_placement

import com.sharmin.posapplication.repositories.CartRepository
import org.mockito.kotlin.mock

class CartListViewModelTest {

    private val cartRepository = mock<CartRepository>()
    private val viewModel = CartListViewModel(cartRepository)
}