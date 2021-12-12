package com.sharmin.posapplication.models.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sharmin.posapplication.models.ProductType

@ProvidedTypeConverter
class  ProductTypeConverter {
    @TypeConverter
    fun toProductType(value: Int): ProductType = enumValues<ProductType>()[value]
    @TypeConverter
    fun fromProductType(value: ProductType): Int = value.ordinal
}