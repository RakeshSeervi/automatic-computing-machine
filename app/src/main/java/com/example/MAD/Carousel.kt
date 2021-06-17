package com.example.MAD

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.MAD.databinding.ActivityCarouselBinding

class Carousel : AppCompatActivity() {
    lateinit var binding: ActivityCarouselBinding
    lateinit var mainHandler: Handler
    private val images = listOf<Int>(R.drawable.cmrit1, R.drawable.cmrit2, R.drawable.cmrit3)

    private val startCarousel = object : Runnable {
        override fun run() {
            showRandomImage()
            mainHandler.postDelayed(this, 3000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarouselBinding.inflate(layoutInflater)
        mainHandler = Handler(Looper.getMainLooper())
        setContentView(binding.root)
    }

    fun showRandomImage() {
        binding.imageView.setImageResource(images.random())
    }

    fun onClickListener(view: View) {
        binding.playCarousel.visibility = View.GONE
        mainHandler.post(startCarousel)
    }
}