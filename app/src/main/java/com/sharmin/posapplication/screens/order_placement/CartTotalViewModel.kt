package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sharmin.posapplication.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartTotalViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartTotal: LiveData<Int>
        get() = cartRepository.cartTotalLiveData
}