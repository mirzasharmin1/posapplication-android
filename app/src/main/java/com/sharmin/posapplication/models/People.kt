package com.sharmin.posapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class People(
    @PrimaryKey val id: Int,
    val role: Role,
    val name: String
)