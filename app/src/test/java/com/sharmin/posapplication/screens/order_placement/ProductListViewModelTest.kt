package com.sharmin.posapplication.screens.order_placement

import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.ProductRepository
import org.mockito.kotlin.mock

class ProductListViewModelTest {

    private val productRepository = mock<ProductRepository>()
    private val cartRepository = mock<CartRepository>()
    private val productType = ProductType.DRINKS
    val viewModel = ProductListViewModel(productRepository, cartRepository, productType)
}