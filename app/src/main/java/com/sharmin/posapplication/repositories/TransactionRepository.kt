package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
import com.sharmin.posapplication.db.models.Transaction
import com.sharmin.posapplication.db.models.TransactionItem
import com.sharmin.posapplication.db.models.TransactionStatus
import com.sharmin.posapplication.domain.models.CartItem
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val transactionItemDao: TransactionItemDao,
    private val cartRepository: CartRepository,
    private val peopleRepository: PeopleRepository
) {

    suspend fun createTransaction(cartItems: List<CartItem>): Boolean {
        val people = peopleRepository.getCurrentUser() ?: return false

        return try {
            val transaction = Transaction(
                people.id,
                cartRepository.getCartTotal(),
                0,
                Date(),
                TransactionStatus.SUCCESSFUL
            )
            val transactionId = transactionDao.insert(transaction)

            val transactionItems = cartItems.map {
                TransactionItem(transactionId.toInt(), it.product.id, it.product.price, it.quantity)
            }

            transactionItemDao.insertAll(*transactionItems.toTypedArray())
            true
        } catch (e: Exception) { false }
    }
}