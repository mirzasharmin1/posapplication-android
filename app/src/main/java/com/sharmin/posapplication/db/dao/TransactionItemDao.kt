package com.sharmin.posapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.TransactionItem
import com.sharmin.posapplication.db.models.TransactionItemWithProductAndTransaction
import com.sharmin.posapplication.db.models.TransactionWithTransactionItems

@Dao
interface TransactionItemDao {

    @androidx.room.Transaction
    @Query("SELECT * FROM `TransactionItem`")
    fun getTransactionItems(): List<TransactionItemWithProductAndTransaction>

    @Insert
    fun insert(vararg transactionItem: TransactionItem)

    @Insert
    fun insertAll(vararg transactionItems: TransactionItem)
}
