package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class People(
    val role: Role,
    val name: String?
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}