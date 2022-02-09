package com.sharmin.posapplication.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity
data class Product(
    val name: String,
    val productType: ProductType,
    val price: Int,
    val unitsAvailable: Int,
    val productImg: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}