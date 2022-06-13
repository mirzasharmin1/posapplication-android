package com.sharmin.posapplication.db.converters

import com.sharmin.posapplication.db.models.TransactionStatus
import org.junit.Test
import kotlin.test.assertEquals

class TransactionStatusConverterTest {

    private val converter = TransactionStatusConverter()

    @Test
    fun `toTransactionStatus converts ordinal value to TransactionStatus correctly`() {
        val ordinalValue = 1

        val resultEnum = converter.toTransactionStatus(ordinalValue)

        assertEquals(resultEnum, TransactionStatus.SUCCESSFUL)
    }

    @Test
    fun `fromTransactionStatus converts TransactionStatus to ordinal value correctly`() {
        val transactionStatus = TransactionStatus.SUCCESSFUL

        val resultOrdinalValue = converter.fromTransactionStatus(transactionStatus)

        assertEquals(resultOrdinalValue, 1)
    }
}