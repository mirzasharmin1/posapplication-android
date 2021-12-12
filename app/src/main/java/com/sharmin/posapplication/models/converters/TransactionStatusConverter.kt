package com.sharmin.posapplication.models.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sharmin.posapplication.models.TransactionStatus

@ProvidedTypeConverter
class  TransactionStatusConverter {
    @TypeConverter
    fun toTransactionStatus(value: Int): TransactionStatus = enumValues<TransactionStatus>()[value]
    @TypeConverter
    fun fromTransactionStatus(value: TransactionStatus): Int = value.ordinal
}