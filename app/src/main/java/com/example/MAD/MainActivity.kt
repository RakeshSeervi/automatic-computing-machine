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
        when (view.id) {
            R.id.card_button -> {
                val intent = Intent(this, Card::class.java)
                startActivity(intent)
            }
            R.id.dialer_button -> {
                val intent = Intent(this, dialer::class.java)
                startActivity(intent)
            }
            R.id.auth_button -> {
                val intent = Intent(this, Auth::class.java)
                startActivity(intent)
            }
        }
    }
}