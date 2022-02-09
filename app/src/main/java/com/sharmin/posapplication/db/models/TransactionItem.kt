package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class TransactionItem (
    val transactionId: Int,
    val productId: Int,
    val pricePerUnit: Int,
    val units: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}