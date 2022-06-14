package com.sharmin.posapplication.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class ProductRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val productDao = mock<ProductDao>()
    private val repository = ProductRepository(productDao)

    @Test
    fun `getProductsByType calls getProductsByType with the correct ProductType and returns the result`() {
        val productsLiveData = MutableLiveData<List<Product>>()
        val productType = ProductType.DRINKS
        productsLiveData.value = listOf(
            Product("Product 1", ProductType.DRINKS, 100, 2, "img1.jpg").apply { id = 1 },
            Product("Product 2", ProductType.DRINKS, 50, 2, "img2.jpg").apply { id = 2 }
        )
        whenever(productDao.getProductsByType(productType)).thenReturn(productsLiveData)

        val result = repository.getProductsByType(productType)

        verify(productDao).getProductsByType(productType)
        assertEquals(productsLiveData, result)
    }

    @Test
    fun `getAllProducts calls getAllProducts and returns the result`() {
        val productsLiveData = MutableLiveData<List<Product>>()
        productsLiveData.value = listOf(
            Product("Product 1", ProductType.DRINKS, 100, 2, "img1.jpg").apply { id = 1 },
            Product("Product 2", ProductType.ICE_CREAMS, 50, 2, "img2.jpg").apply { id = 2 }
        )
        whenever(productDao.getAllProducts()).thenReturn(productsLiveData)

        val result = repository.getAllProducts()

        verify(productDao).getAllProducts()
        assertEquals(productsLiveData, result)
    }
}