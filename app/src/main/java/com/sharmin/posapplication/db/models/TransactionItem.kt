package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class TransactionItem (
    @PrimaryKey val id: Int,
    val transactionId: Int,
    val productId: Int,
    val pricePerUnit: Int,
    val units: Int
)