package com.sharmin.posapplication.screens.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sharmin.posapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }
}