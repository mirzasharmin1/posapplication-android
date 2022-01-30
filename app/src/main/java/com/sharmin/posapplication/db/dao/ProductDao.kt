package com.sharmin.posapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.sharmin.posapplication.db.models.Product

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM Product")
    fun getProducts(): LiveData<List<Product>>

    @Insert
    fun insert(product: Product)

    @Insert
    fun insertAll(vararg products: Product)
}