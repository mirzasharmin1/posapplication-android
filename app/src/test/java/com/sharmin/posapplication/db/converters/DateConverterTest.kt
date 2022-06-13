package com.sharmin.posapplication.db.converters

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class DateConverterTest {

    private val dateConverter = DateConverter()

    @Test
    fun `fromTimestamp can convert timestamp to date correctly`() {
        val timestamp = 1655078400L
        val expectedDate = Date(timestamp)

        val resultDate = dateConverter.fromTimestamp(timestamp)

        assertEquals(resultDate, expectedDate)
    }

    @Test
    fun `dateToTimestamp can convert date to timestamp correctly`() {
        val expectedTimestamp = 1655078400L
        val date = Date(expectedTimestamp)

        val resultTimestamp = dateConverter.dateToTimestamp(date)

        assertEquals(expectedTimestamp, resultTimestamp)
    }
}