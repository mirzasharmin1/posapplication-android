package com.sharmin.posapplication.screens.product_crud

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val products: LiveData<List<Product>>
        get() = productRepository.getALlProducts()
}
