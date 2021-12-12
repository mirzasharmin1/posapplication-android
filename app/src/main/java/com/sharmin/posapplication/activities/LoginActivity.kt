package com.sharmin.posapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.sharmin.posapplication.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    private fun setListeners() {
        val headlineTextView = findViewById<TextView>(R.id.headline)
        val userInput = findViewById<EditText>(R.id.userInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val button = findViewById<Button>(R.id.submitBtn)

        headlineTextView.setOnLongClickListener {
            navigateToSettingActivity()
            true
        }

        button.setOnClickListener {
            showToastMessage()
            //navigateToOrderActivity()
        }
    }

    private fun navigateToSettingActivity() {
        startActivity(Intent(this, SettingActivity::class.java))
    }

    private fun navigateToOrderActivity() {
        startActivity(Intent(this, OrderActivity::class.java))
    }

    private fun showToastMessage() {
        val msg = "Sharmin has clicked the login button"
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}