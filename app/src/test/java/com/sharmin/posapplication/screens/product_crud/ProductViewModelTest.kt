package com.sharmin.posapplication.screens.product_crud

import com.sharmin.posapplication.repositories.ProductRepository
import org.mockito.kotlin.mock

class ProductViewModelTest {

    private val productRepository = mock<ProductRepository>()
    private val viewModel = ProductViewModel(productRepository)
}