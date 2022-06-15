package com.sharmin.posapplication.screens.order_placement

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.domain.models.CartItem
import com.sharmin.posapplication.ext.getOrAwaitValue
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class OrderPlacementViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var transactionRepository: TransactionRepository
    private lateinit var cartRepository: CartRepository
    private lateinit var viewModel: OrderPlacementViewModel

    private val cartItems = listOf(
        CartItem(
            Product("Product One", ProductType.ICE_CREAMS, 100, 10, "product1img.jpg").apply { id = 1 },
            1
        ),
        CartItem(
            Product("Product Two", ProductType.DRINKS, 50, 5, "product2img.jpg").apply { id = 2 },
            2
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        transactionRepository = mock()
        cartRepository = mock()
        viewModel = OrderPlacementViewModel(transactionRepository, cartRepository, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `resetTransactionSuccessful sets transactionSuccessful livedata to false`() {
        viewModel.transactionSuccessful.value = true

        viewModel.resetTransactionSuccessful()

        val transactionSuccessfulValue = viewModel.transactionSuccessful.getOrAwaitValue()
        assertEquals(transactionSuccessfulValue, false)
    }

    @Test
    fun `resetTransactionFailed sets transactionFailed livedata to false`() {
        viewModel.transactionFailed.value = true

        viewModel.resetTransactionFailed()

        val transactionFailedValue = viewModel.transactionFailed.getOrAwaitValue()
        assertEquals(transactionFailedValue, false)
    }

    @Test
    fun `createTransaction Given createTransaction in transactionRepository is successful Then sets transactionSuccessful livedata to true and calls clearCart on cartRepository`() = runTest {
        val cartItemsLiveData = MutableLiveData<List<CartItem>>().apply { value = cartItems }
        whenever(cartRepository.cartItemsLiveData).thenReturn(cartItemsLiveData)
        whenever(transactionRepository.createTransaction(any())).thenReturn(true)

        viewModel.createTransaction()

        verify(transactionRepository).createTransaction(cartItems)
        val transactionSuccessfulResult = viewModel.transactionSuccessful.getOrAwaitValue()
        assertEquals(transactionSuccessfulResult, true)
        verify(cartRepository).clearCart()
    }

    @Test
    fun `createTransaction Given createTransaction in transactionRepository is failed Then sets transactionFailed livedata to true`() = runTest {
        val cartItemsLiveData = MutableLiveData<List<CartItem>>().apply { value = cartItems }
        whenever(cartRepository.cartItemsLiveData).thenReturn(cartItemsLiveData)
        whenever(transactionRepository.createTransaction(any())).thenReturn(false)

        viewModel.createTransaction()
        verify(transactionRepository).createTransaction(cartItems)
        val transactionFailedResult = viewModel.transactionFailed.getOrAwaitValue()
        assertEquals(transactionFailedResult, true)

    }
}