package com.sharmin.posapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity
data class Transaction(
    @PrimaryKey val id: Int,
    val peopleId: Int,
    val amountInserted: Int,
    val amountRefunded: Int,
    val createdAt: Date,
    val status: TransactionStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "transactionId"
    )
    val transactionItems: List<TransactionItem>,
    @Relation(
        parentColumn = "id",
        entityColumn = "peopleId"
    )
    val people: People
)