package com.sharmin.posapplication.db.converters

import com.sharmin.posapplication.db.models.Role
import org.junit.Test
import kotlin.test.assertEquals

class RoleConverterTest {

    private val converter = RoleConverter()

    @Test
    fun `toRole converts ordinal value to Role correctly`() {
        val ordinalValue = 2

        val resultEnum = converter.toRole(ordinalValue)

        assertEquals(resultEnum, Role.BRANCH_MANAGER)
    }

    @Test
    fun `fromProductType converts ProductType to ordinal value correctly`() {
        val role = Role.BRANCH_MANAGER

        val resultOrdinalValue = converter.fromRole(role)

        assertEquals(resultOrdinalValue, 2)
    }
}