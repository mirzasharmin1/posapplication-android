package com.sharmin.posapplication.repositories

import com.sharmin.posapplication.db.dao.TransactionDao
import com.sharmin.posapplication.db.dao.TransactionItemDao
import org.mockito.kotlin.mock

class TransactionRepositoryTest {

    private val transactionDao = mock<TransactionDao>()
    private val transactionItemDao = mock<TransactionItemDao>()
    private val cartRepository = mock<CartRepository>()
    private val peopleRepository = mock<PeopleRepository>()
    private val repository = TransactionRepository(transactionDao, transactionItemDao, cartRepository, peopleRepository)
}