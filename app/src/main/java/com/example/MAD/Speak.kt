package com.example.MAD

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.MAD.databinding.ActivitySpeakBinding

class Speak : AppCompatActivity() {
    lateinit var textToSpeech: TextToSpeech
    lateinit var binding: ActivitySpeakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Text To Speech"

        binding = ActivitySpeakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textToSpeech = TextToSpeech(this) {
            Toast.makeText(this, "TTS initialised successfully!", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun speak(view: View) {
        textToSpeech.speak(binding.inputText.text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}