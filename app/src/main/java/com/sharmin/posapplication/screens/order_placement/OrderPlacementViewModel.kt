package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderPlacementViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {

    // LiveData added here
    val transactionSuccessful = MutableLiveData<Boolean>(false)
    val transactionFailed = MutableLiveData<Boolean>(false)

    fun createTransaction() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
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
}