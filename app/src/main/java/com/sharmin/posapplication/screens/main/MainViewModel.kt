package com.sharmin.posapplication.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    val products: LiveData<List<Product>>
        get() = productRepository.getProducts()
}