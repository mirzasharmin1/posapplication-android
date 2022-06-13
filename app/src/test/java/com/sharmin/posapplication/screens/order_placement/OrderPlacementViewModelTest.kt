package com.sharmin.posapplication.screens.order_placement

import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.TransactionRepository
import org.mockito.kotlin.mock

class OrderPlacementViewModelTest {

    private val transactionRepository = mock<TransactionRepository>()
    private val cartRepository = mock<CartRepository>()
    private val viewModel = OrderPlacementViewModel(transactionRepository, cartRepository)
}