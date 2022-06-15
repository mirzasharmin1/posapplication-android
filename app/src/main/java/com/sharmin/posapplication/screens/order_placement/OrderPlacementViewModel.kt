package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.TransactionRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderPlacementViewModel @AssistedInject constructor(
    private val transactionRepository: TransactionRepository,
    private val cartRepository: CartRepository,
    @Assisted private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    @AssistedFactory
    interface OrderPlacementViewModelFactory {
        fun create(dispatcher: CoroutineDispatcher): OrderPlacementViewModel
    }

    // LiveData added here
    val transactionSuccessful = MutableLiveData(false)
    val transactionFailed = MutableLiveData(false)

    fun createTransaction() {
        viewModelScope.launch {
            withContext(dispatcher) {
                val result = transactionRepository.createTransaction(cartRepository.cartItemsLiveData.value!!)

                if (result) {
                    transactionSuccessful.postValue(true)
                    cartRepository.clearCart()
                } else {
                    transactionFailed.postValue(true)
                }
            }
        }
    }

    fun resetTransactionSuccessful() {
        transactionSuccessful.value = false
    }

    fun resetTransactionFailed() {
        transactionFailed.value = false
    }

    companion object {
        fun provideFactory(
            assistedFactory: OrderPlacementViewModel.OrderPlacementViewModelFactory,
            dispatcher: CoroutineDispatcher = Dispatchers.IO
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(dispatcher) as T
            }
        }
    }
}