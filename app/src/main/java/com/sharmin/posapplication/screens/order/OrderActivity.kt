package com.sharmin.posapplication.screens.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sharmin.posapplication.R
import com.sharmin.posapplication.db.models.ProductType
import com.sharmin.posapplication.screens.order_placement.OrderPlacementActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        bindListeners()
    }

    private fun bindListeners() {
        findViewById<Button>(R.id.order_macarons).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.MACARONS)
        }

        findViewById<Button>(R.id.order_drinks).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.DRINKS)
        }

        findViewById<Button>(R.id.order_desserts).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.DESSERTS)
        }

        findViewById<Button>(R.id.order_icecreams).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.ICE_CREAMS)
        }

        findViewById<Button>(R.id.order_thai_dishes).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.THAI_DISHES)
        }

        findViewById<Button>(R.id.order_fruits).setOnClickListener {
            navigateToOrderPlacementActivity(ProductType.FRUITS)
        }
    }

    private fun navigateToOrderPlacementActivity(productType: ProductType) {
        val intent = Intent(this, OrderPlacementActivity::class.java)
        intent.putExtra(OrderPlacementActivity.BUNDLE_PRODUCT_FILTER_TYPE, productType)
        startActivity(intent)
    }
}