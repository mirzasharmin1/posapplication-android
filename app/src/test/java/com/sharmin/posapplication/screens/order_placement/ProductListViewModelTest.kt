package com.sharmin.posapplication.screens.order_placement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.ext.getOrAwaitValue
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.ProductRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class ProductListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productRepository: ProductRepository
    private lateinit var cartRepository: CartRepository
    private val productType = ProductType.DRINKS
    private lateinit var viewModel: ProductListViewModel

    private val products = listOf(
        Product("Product 1", ProductType.DESSERTS, 100, 50, "img1.jpg"),
        Product("Product 2", ProductType.DESSERTS, 10, 20, "img2.jpg")
    )

    @Before
    fun setUp() {
        val productsLiveData = MutableLiveData<List<Product>>().apply {
            value = products
        }
        productRepository = mock()
        cartRepository = mock()
        whenever(productRepository.getProductsByType(productType)).thenReturn(productsLiveData)
        viewModel = ProductListViewModel(productRepository, cartRepository, productType)
    }

    @Test
    fun `products calls getProductsByType from productRepository with correct parameter`() {
        val productsValue = viewModel.products.getOrAwaitValue()

        verify(productRepository).getProductsByType(productType)
        assertEquals(productsValue, products)
    }

    @Test
    fun `addProductToCart call addItemToCart in cartRepository with correct parameters`() {
        val product = Product("Product 3", ProductType.DESSERTS, 20, 30, "img3.jpg")

        viewModel.addProductToCart(product)

        verify(cartRepository).addItemToCart(product, 1)
    }

    @Test
    fun `removeProductFromCart call reduceItemFromCart in cartRepository with correct parameters`() {
        val product = Product("Product 3", ProductType.DESSERTS, 20, 30, "img3.jpg")

        viewModel.removeProductFromCart(product)

        verify(cartRepository).reduceItemFromCart(product, 1)
    }
}