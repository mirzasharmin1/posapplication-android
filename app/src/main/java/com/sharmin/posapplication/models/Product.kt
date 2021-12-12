package com.sharmin.posapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val productType: ProductType,
    val price: Int,
    val unitsAvailable: Int
)