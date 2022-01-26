package com.sharmin.posapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.Transaction
import com.sharmin.posapplication.db.models.TransactionWithTransactionItems

@Dao
interface TransactionDao {

    @androidx.room.Transaction
    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): List<TransactionWithTransactionItems>

    @Insert
    fun insert(vararg transaction: Transaction)
}