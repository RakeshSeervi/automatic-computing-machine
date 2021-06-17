package com.example.MAD

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.MAD.databinding.ActivityCounterBinding

class Counter : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private var isRunning: Boolean = false
    lateinit var mainHandler: Handler
    var count: Int = 0

    private val startCounter = object : Runnable {
        override fun run() {
            updateCounter()
            mainHandler.postDelayed(this, 1000)
        }
    }

    fun updateCounter() {
        count += 1
        binding.counterText.text = count.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        mainHandler = Handler(Looper.getMainLooper())
        setContentView(binding.root)
    }

    fun onClickListener(view: View) {
        if (isRunning) {
            mainHandler.removeCallbacks(startCounter)
            binding.toggleBtn.text = getString(R.string.start)
            count = -1
            updateCounter()
        } else {
            binding.toggleBtn.text = getString(R.string.stop)
            mainHandler.post(startCounter)
        }
        isRunning = !isRunning
    }
}