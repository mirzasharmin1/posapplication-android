package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
import com.sharmin.posapplication.db.models.*
import com.sharmin.posapplication.domain.models.CartItem
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.*
import java.lang.RuntimeException
import java.util.*
import kotlin.test.assertEquals

class TransactionRepositoryTest {

    private val transactionDao = mock<TransactionDao>()
    private val transactionItemDao = mock<TransactionItemDao>()
    private val cartRepository = mock<CartRepository>()
    private val peopleRepository = mock<PeopleRepository>()
    private val repository = TransactionRepository(transactionDao, transactionItemDao, cartRepository, peopleRepository)

    @Test
    fun `createTransaction Given getCurrentUser calls returns null Then returns false`() {
        val cartItems = listOf<CartItem>(
            CartItem(
                Product("Product One", ProductType.ICE_CREAMS, 100, 10, "product1img.jpg").apply { id = 1 },
                1
            ),
            CartItem(
                Product("Product Two", ProductType.DRINKS, 50, 5, "product2img.jpg").apply { id = 2 },
                2
            ),
            CartItem(
                Product("Product Three", ProductType.DESSERTS, 20, 20, "product3img.jpg").apply { id = 3 },
                3
            )
        )
        whenever(peopleRepository.getCurrentUser()).thenReturn(null)

        val result = runBlocking { repository.createTransaction(cartItems) }

        assertEquals(result, false)
    }

    @Test
    fun `createTransaction Given getCurrentUser throws exception Then returns false`() {
        val cartItems = listOf<CartItem>(
            CartItem(
                Product("Product One", ProductType.ICE_CREAMS, 100, 10, "product1img.jpg").apply { id = 1 },
                1
            ),
            CartItem(
                Product("Product Two", ProductType.DRINKS, 50, 5, "product2img.jpg").apply { id = 2 },
                2
            ),
            CartItem(
                Product("Product Three", ProductType.DESSERTS, 20, 20, "product3img.jpg").apply { id = 3 },
                3
            )
        )
        whenever(peopleRepository.getCurrentUser()).thenThrow(RuntimeException("DUMMY"))

        val result = runBlocking { repository.createTransaction(cartItems) }

        assertEquals(result, false)
    }

    @Test
    fun `createTransaction calls insert on transactionDao and calls insertAll on transactionItemDao and calls getCartTotal on cartRepository with correct parameters and returns true`() {
        val people = People(Role.ADMIN, "User One").apply { id = 1 }
        val cartTotal = 260
        val transactionId = 5L
        val cartItems = listOf<CartItem>(
            CartItem(
                Product("Product One", ProductType.ICE_CREAMS, 100, 10, "product1img.jpg").apply { id = 1 },
                1
            ),
            CartItem(
                Product("Product Two", ProductType.DRINKS, 50, 5, "product2img.jpg").apply { id = 2 },
                2
            ),
            CartItem(
                Product("Product Three", ProductType.DESSERTS, 20, 20, "product3img.jpg").apply { id = 3 },
                3
            )
        )
        val expectedTransactionItems = listOf(
            TransactionItem(5, 1, 100, 1),
            TransactionItem(5, 2, 50, 2),
            TransactionItem(5, 3, 20, 3)
        )
        whenever(cartRepository.getCartTotal()).thenReturn(cartTotal)
        whenever(peopleRepository.getCurrentUser()).thenReturn(people)
        whenever(transactionDao.insert(any())).thenReturn(transactionId)

        val result = runBlocking { repository.createTransaction(cartItems) }

        verify(peopleRepository).getCurrentUser()
        val transactionCaptor = argumentCaptor<Transaction>()
        verify(transactionDao).insert(transactionCaptor.capture())
        val actualTransaction = transactionCaptor.firstValue
        verify(transactionItemDao).insertAll(expectedTransactionItems[0], expectedTransactionItems[1], expectedTransactionItems[2])
        assertEquals(actualTransaction.peopleId, people.id)
        assertEquals(actualTransaction.amountInserted, cartTotal)
        assertEquals(actualTransaction.amountRefunded, 0)
        assertEquals(actualTransaction.status, TransactionStatus.SUCCESSFUL)
        assertEquals(result, true)
    }
}