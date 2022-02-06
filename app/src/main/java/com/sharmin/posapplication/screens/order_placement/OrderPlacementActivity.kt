package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ActivityOrderPlacementBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderPlacementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderPlacementBinding

    @Inject
    lateinit var productListFragment: ProductListFragment

    @Inject
    lateinit var cartListFragment: CartListFragment

    @Inject
    lateinit var cartTotalFragment: CartTotalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderPlacementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.product_list_fragment, productListFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cart_list_fragment, cartListFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cart_total_fragment, cartTotalFragment)
            .commit()
    }

    companion object {
        const val BUNDLE_PRODUCT_FILTER_TYPE = "product_type"
    }
}