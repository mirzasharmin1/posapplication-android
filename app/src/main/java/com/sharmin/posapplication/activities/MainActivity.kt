package com.sharmin.posapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.sharmin.posapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        val headlineTextView = findViewById<TextView>(R.id.headline)
        val userInput = findViewById<EditText>(R.id.userInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val button = findViewById<Button>(R.id.submitBtn)

        headlineTextView.setCustomLongClickListener(6000) {
            navigateToSettingActivity()
        }

        button.setOnClickListener {
            //showToastMessage()
            navigateToOrderActivity()
        }
    }

    fun View.setCustomLongClickListener(clickDuration: Long, listener: () -> Unit) {
        setOnTouchListener(object : View.OnTouchListener {

            private val longClickDuration = clickDuration
            private val handler = Handler()

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed({ listener.invoke() }, longClickDuration)
                } else if (event?.action == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null)
                }
                return true
            }
        })
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