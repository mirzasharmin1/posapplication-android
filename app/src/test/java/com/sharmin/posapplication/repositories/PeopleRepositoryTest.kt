package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.ProductDao
import org.mockito.kotlin.mock

class PeopleRepositoryTest {

    private val productDao = mock<ProductDao>()
    private val repository = ProductRepository(productDao)
}