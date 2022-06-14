package com.sharmin.posapplication.screens.order_placement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.TransactionRepository
import org.junit.Rule
import org.mockito.kotlin.mock

class OrderPlacementViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val transactionRepository = mock<TransactionRepository>()
    private val cartRepository = mock<CartRepository>()
    private val viewModel = OrderPlacementViewModel(transactionRepository, cartRepository)
}