package com.sharmin.posapplication.screens.product_crud

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sharmin.posapplication.repositories.ProductRepository
import org.junit.Rule
import org.mockito.kotlin.mock

class ProductViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val productRepository = mock<ProductRepository>()
    private val viewModel = ProductViewModel(productRepository)
}