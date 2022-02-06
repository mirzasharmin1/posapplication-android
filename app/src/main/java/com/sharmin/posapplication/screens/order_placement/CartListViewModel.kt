package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sharmin.posapplication.domain.models.CartItem
import com.sharmin.posapplication.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartListViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartItems: LiveData<List<CartItem>>
        get() = cartRepository.cartItemsLiveData
}
