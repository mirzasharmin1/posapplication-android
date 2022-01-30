package com.sharmin.posapplication.repositories

import androidx.lifecycle.LiveData
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.models.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    fun getProducts(): LiveData<List<Product>> {
        return productDao.getProducts()
    }
}