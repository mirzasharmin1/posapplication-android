package com.sharmin.posapplication.screens.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sharmin.posapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    }
}