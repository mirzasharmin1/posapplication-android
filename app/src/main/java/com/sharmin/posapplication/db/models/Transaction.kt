package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    @PrimaryKey val id: Int,
    val peopleId: Int,
    val amountInserted: Int,
    val amountRefunded: Int,
    val createdAt: Date,
    val status: TransactionStatus
)