package com.sharmin.posapplication.db.converters

import com.sharmin.posapplication.db.models.ProductType
import org.junit.Test
import kotlin.test.assertEquals

class ProductTypeConverterTest {

    private val converter = ProductTypeConverter()

    @Test
    fun `toProductType converts ordinal value to ProductType correctly`() {
        val ordinalValue = 3

        val resultEnum = converter.toProductType(ordinalValue)

        assertEquals(resultEnum, ProductType.ICE_CREAMS)
    }

    @Test
    fun `fromProductType converts ProductType to ordinal value correctly`() {
        val productType = ProductType.ICE_CREAMS

        val resultOrdinalValue = converter.fromProductType(productType)

        assertEquals(resultOrdinalValue, 3)
    }
}