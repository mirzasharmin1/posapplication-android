package com.sharmin.posapplication.db.converters

import androidx.room.TypeConverter
import com.sharmin.posapplication.db.models.ProductType

class  ProductTypeConverter {

    @TypeConverter
    fun toProductType(value: Int): ProductType = enumValues<ProductType>()[value]

    @TypeConverter
    fun fromProductType(value: ProductType): Int = value.ordinal
}