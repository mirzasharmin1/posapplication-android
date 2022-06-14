package com.sharmin.posapplication.screens.product_crud

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.ext.getOrAwaitValue
import com.sharmin.posapplication.repositories.ProductRepository
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class ProductViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val productRepository = mock<ProductRepository>()
    private val viewModel = ProductViewModel(productRepository)

    private val products = listOf(
        Product("Product 1", ProductType.DESSERTS, 100, 50, "img1.jpg"),
        Product("Product 2", ProductType.DRINKS, 10, 20, "img2.jpg")
    )

    @Test
    fun `products calls getAllProducts from productRepository and returns the result`() {
        val productsLiveData = MutableLiveData<List<Product>>().apply {
            value = products
        }
        whenever(productRepository.getAllProducts()).thenReturn(productsLiveData)

        val productsLiveDataResult = viewModel.products

        val productsResult = productsLiveDataResult.getOrAwaitValue()
        assertEquals(productsResult, products)
    }
}