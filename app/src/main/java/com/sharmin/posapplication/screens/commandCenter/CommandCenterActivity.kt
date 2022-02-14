package com.sharmin.posapplication.screens.commandCenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.databinding.ActivityCommandCenterBinding
import com.sharmin.posapplication.screens.addProduct.AddProductActivity
import com.sharmin.posapplication.screens.order.OrderActivity
import com.sharmin.posapplication.screens.product_crud.ProductCRUDActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommandCenterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommandCenterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommandCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.newOrder.setOnClickListener {
            navigateToOrderActivity()
        }

        binding.addProduct.setOnClickListener {
            navigateToAddProductActivity()
        }

        binding.listProducts.setOnClickListener {
            navigateToProductCrudActivity()
        }
    }

    private fun navigateToOrderActivity() {
        startActivity(Intent(this, OrderActivity::class.java))
    }

    private fun navigateToAddProductActivity() {
        startActivity(Intent(this, AddProductActivity::class.java))
    }

    private fun navigateToProductCrudActivity() {
        startActivity(Intent(this, ProductCRUDActivity::class.java))
    }
}