package com.sharmin.posapplication.screens.product_crud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ActivityProductCrudBinding
import com.sharmin.posapplication.db.models.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductCRUDActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductCrudBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val productFragment = ProductFragment(::showProductFragment)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.product_list_fragment, productFragment)
            .commit()
    }

    private fun showProductFragment(product: Product) {
        val showProductFragment = ShowProductFragment(product)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.product_detail_fragment, showProductFragment)
            .commit()
    }
}
