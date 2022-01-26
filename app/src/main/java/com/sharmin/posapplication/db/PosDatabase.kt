package com.sharmin.posapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sharmin.posapplication.db.converters.DateConverter
import com.sharmin.posapplication.db.converters.ProductTypeConverter
import com.sharmin.posapplication.db.converters.RoleConverter
import com.sharmin.posapplication.db.converters.TransactionStatusConverter
import com.sharmin.posapplication.db.dao.ProductDao
import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
import com.sharmin.posapplication.db.models.*

@Database(
    entities = [
        People::class,
        Product::class,
        Transaction::class,
        TransactionItem::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class, ProductTypeConverter::class, RoleConverter::class, TransactionStatusConverter::class)
abstract class PosDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun transactionDao(): TransactionDao

    abstract fun transactionItemDao(): TransactionItemDao
}