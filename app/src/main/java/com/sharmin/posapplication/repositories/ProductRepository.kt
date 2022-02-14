package com.sharmin.posapplication.repositories

import androidx.lifecycle.LiveData
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    fun getProductsByType(productType: ProductType): LiveData<List<Product>> {
        return productDao.getProductsByType(productType)
    }

    fun getALlProducts(): LiveData<List<Product>> {
        return productDao.getAllProducts()
    }
}