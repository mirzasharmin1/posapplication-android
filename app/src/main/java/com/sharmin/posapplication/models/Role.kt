package com.sharmin.posapplication.models

import androidx.room.Entity

@Entity
enum class Role {
    ADMIN, SALES_STAFF, BRANCH_MANAGER, DISTRIBUTOR
}