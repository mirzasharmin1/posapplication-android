package com.sharmin.posapplication.screens.order_placement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderPlacementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placement)
    }

    companion object {
        val BUNDLE_PRODUCT_FILTER_TYPE = "product_type"
    }
}