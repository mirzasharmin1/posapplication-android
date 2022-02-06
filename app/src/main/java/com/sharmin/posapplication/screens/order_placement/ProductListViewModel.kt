package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    val products: LiveData<List<Product>>
        get() = productRepository.getProducts()

    fun addProductToCart(product: Product) {
        cartRepository.addItemToCart(product, 1)
    }

    fun removeProductFromCart(product: Product) {
        cartRepository.reduceItemFromCart(product, 1)
    }
}
