package com.sharmin.posapplication.screens.addProduct

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmin.posapplication.databinding.ActivityAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}