package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.databinding.ActivityOrderPlacementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderPlacementActivity : AppCompatActivity() {

    val viewModel: OrderPlacementViewModel by viewModels()
    private lateinit var binding: ActivityOrderPlacementBinding
    private lateinit var cartListAdapter: CartListAdapter
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderPlacementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setUpListeners()
    }

    private fun setupView() {
        productListAdapter = ProductListAdapter(viewModel::addProductToCart, viewModel::removeProductFromCart)
        binding.recyclerViewProductList.adapter = productListAdapter

        cartListAdapter = CartListAdapter()
        binding.recyclerViewCartItemList.adapter = cartListAdapter
    }

    private fun setUpListeners() {
        viewModel.products.observe(this, {
            it
            productListAdapter.submitList(it)
        })

        viewModel.cartItems.observe(this, {
            it
            cartListAdapter.addHeaderAndSubmitList(it)
        })
    }

    companion object {
        const val BUNDLE_PRODUCT_FILTER_TYPE = "product_type"
    }
}