package com.sharmin.posapplication.db.converters

import androidx.room.TypeConverter
import com.sharmin.posapplication.db.models.TransactionStatus

class  TransactionStatusConverter {
    @TypeConverter
    fun toTransactionStatus(value: Int): TransactionStatus = enumValues<TransactionStatus>()[value]
    @TypeConverter
    fun fromTransactionStatus(value: TransactionStatus): Int = value.ordinal
}