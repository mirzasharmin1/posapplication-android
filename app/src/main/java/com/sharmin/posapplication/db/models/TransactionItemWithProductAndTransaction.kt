package com.sharmin.posapplication.db.models

import androidx.room.Embedded
import androidx.room.Relation

class TransactionItemWithProductAndTransaction (
    @Embedded val transactionItem: TransactionItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "id"
    )
    val transaction: Transaction
)