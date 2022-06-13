package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.ProductDao
import org.mockito.kotlin.mock

class ProductRepositoryTest {

    private val productDao = mock<ProductDao>()
    private val repository = ProductRepository(productDao)
}