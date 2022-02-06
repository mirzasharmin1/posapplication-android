package com.sharmin.posapplication.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sharmin.posapplication.databinding.ActivityMainBinding
import com.sharmin.posapplication.screens.order.OrderActivity
import com.sharmin.posapplication.screens.settings.SettingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {

        binding.headline.setOnLongClickListener {
            navigateToSettingActivity()
            true
        }

        binding.submitBtn.setOnClickListener {
            viewModel.handleRememberMe(binding.userInput.text.toString(), binding.passwordInput.text.toString())
            navigateToOrderActivity()
        }
        
        binding.rememberMeCheckbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setRememberMe(isChecked)
        }

        viewModel.userNameLiveData.observe(this, {
            binding.userInput.setText(it)
        })

        viewModel.passwordLiveData.observe(this, {
            binding.passwordInput.setText(it)
        })

        viewModel.rememberMeLiveData.observe(this, {
            binding.rememberMeCheckbox.isChecked = it
        })
    }

    private fun navigateToSettingActivity() {
        startActivity(Intent(this, SettingActivity::class.java))
    }

    private fun navigateToOrderActivity() {
        startActivity(Intent(this, OrderActivity::class.java))
    }
}