package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val productType: ProductType,
    val price: Int,
    val unitsAvailable: Int,
    val productImg: String
)