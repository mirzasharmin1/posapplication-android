package com.sharmin.posapplication.models

import androidx.room.Entity

@Entity
enum class TransactionStatus {
    IN_PROGRESS, SUCCESSFUL, FAILURE
}