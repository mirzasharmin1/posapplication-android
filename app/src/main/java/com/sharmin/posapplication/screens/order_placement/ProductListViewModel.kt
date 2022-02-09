package com.sharmin.posapplication.screens.order_placement

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sharmin.posapplication.db.models.Product
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.repositories.CartRepository
import com.sharmin.posapplication.repositories.ProductRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ProductListViewModel @AssistedInject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    @Assisted private val productType: ProductType
) : ViewModel() {

    @AssistedFactory
    interface ProductListViewModelFactory {
        fun create(productType: ProductType): ProductListViewModel
    }

    val products: LiveData<List<Product>>
        get() = productRepository.getProductsByType(productType)

    fun addProductToCart(product: Product) {
        cartRepository.addItemToCart(product, 1)
    }

    fun removeProductFromCart(product: Product) {
        cartRepository.reduceItemFromCart(product, 1)
    }

    companion object {
        fun provideFactory(
            assistedFactory: ProductListViewModelFactory,
            productType: ProductType
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(productType) as T
            }
        }
    }
}
