package com.sharmin.posapplication.utils

import org.junit.Test
import kotlin.test.assertEquals

class PriceFormatterTest {

    @Test
    fun `formats int price to proper formatted string`() {
        val price = 10

        val formattedStr = PriceFormatter.apply(price)

        assertEquals(formattedStr, "10B")
    }
}