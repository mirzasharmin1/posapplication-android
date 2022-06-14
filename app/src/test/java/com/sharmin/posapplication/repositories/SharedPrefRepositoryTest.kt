package com.sharmin.posapplication.repositories

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SharedPrefRepositoryTest {

    private lateinit var context: Context
    private lateinit var repository: SharedPrefRepository

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setup() {
        sharedPref = mock()
        editor = mock()
        context = mock()
        whenever(context.getSharedPreferences(any(), any())).thenReturn(sharedPref)
        repository = SharedPrefRepository(context)
        whenever(sharedPref.edit()).thenReturn(editor)
    }

    @Test
    fun `store Given String value is passed Then calls putString with the key and value and calls apply`() {
        val key = "Key1"
        val value = "Value1"

        repository.store(key, value)

        verify(editor).putString(key, value)
        verify(editor).apply()
    }

    @Test
    fun `store Given Boolean value is passed Then calls putBoolean with the key and value and calls apply`() {
        val key = "Key1"
        val value = true

        repository.store(key, value)

        verify(editor).putBoolean(key, value)
        verify(editor).apply()
    }

    @Test
    fun `getBoolean Given sharedPref does not contain the key Then returns null`() {
        val key = "Key1"
        whenever(sharedPref.contains(key)).thenReturn(false)

        val result = repository.getBoolean(key)

        assertNull(result)
    }

    @Test
    fun `getBoolean Given sharedPref contains the key Then calls getBoolean on sharedPref and return the result`() {
        val key = "Key1"
        val value = true
        whenever(sharedPref.contains(key)).thenReturn(true)
        whenever(sharedPref.getBoolean(eq(key), any())).thenReturn(value)

        val result = repository.getBoolean(key)

        verify(sharedPref).getBoolean(key, false)
        assertEquals(result, value)
    }

    @Test
    fun `getString Given sharedPref does not contain the key Then returns null`() {
        val key = "Key1"
        whenever(sharedPref.contains(key)).thenReturn(false)

        val result = repository.getString(key)

        assertNull(result)
    }

    @Test
    fun `getString Given sharedPref contains the key Then calls getString on sharedPref and return the result`() {
        val key = "Key1"
        val value = "Value1"
        whenever(sharedPref.contains(key)).thenReturn(true)
        whenever(sharedPref.getString(eq(key), any())).thenReturn(value)

        val result = repository.getString(key)

        verify(sharedPref).getString(key, "")
        assertEquals(result, value)
    }
}