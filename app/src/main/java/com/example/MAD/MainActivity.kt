package com.example.MAD

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.MAD.Auth.Auth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickListener(view: View) {
        val intent: Intent = when (view.id) {
            R.id.card_button -> Intent(this, Card::class.java)
            R.id.dialer_button -> Intent(this, dialer::class.java)
            R.id.auth_button -> Intent(this, Auth::class.java)
            R.id.tts -> Intent(this, Speak::class.java)
            R.id.counter_btn -> Intent(this, Counter::class.java)
            else -> Intent(this, Carousel::class.java)
        }

        startActivity(intent)
    }
}