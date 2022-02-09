package com.sharmin.posapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM Product")
    fun getAllProducts(): LiveData<List<Product>>

    @Transaction
    @Query("SELECT * FROM Product WHERE productType = :productType")
    fun getProductsByType(productType: ProductType): LiveData<List<Product>>

    @Insert
    fun insert(product: Product)

    @Insert
    fun insertAll(vararg products: Product)
}