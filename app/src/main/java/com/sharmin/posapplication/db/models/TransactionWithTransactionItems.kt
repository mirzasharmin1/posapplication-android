package com.sharmin.posapplication.db.models

import androidx.room.Embedded
import androidx.room.Relation

class TransactionWithTransactionItems (
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "id",
        entityColumn = "transactionId"
    )
    val transactionItems: List<TransactionItem>,
    @Relation(
        parentColumn = "peopleId",
        entityColumn = "id"
    )
    val people: People
)