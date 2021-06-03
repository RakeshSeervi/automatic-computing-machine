package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var phoneNumberView: TextView
    var phoneNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialer)
        phoneNumberView = findViewById(R.id.phoneNumberView)
    }

    fun onClickListener(button: View){
        when(button.id){
            R.id.dialBtn -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:$phoneNumber")
                    }
                if (intent.resolveActivity(packageManager) != null) {
                    println("success")
                    startActivity(intent)
                }
                println("done")
            }
            R.id.addToContactsBtn -> {
                val intent = Intent(Intent.ACTION_INSERT).apply {
                    type = ContactsContract.Contacts.CONTENT_TYPE
                    putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber)
                }
                if (intent.resolveActivity(packageManager)!=null) {
                    startActivity(intent)
                    println("success")
                }
                println("done")
            }
            R.id.backspaceBtn -> {
                phoneNumber = phoneNumber.dropLast(1)
                when(phoneNumber.length){
                    3, 6 -> phoneNumberView.text = phoneNumberView.text.dropLast(2)
                    else -> phoneNumberView.text = phoneNumberView.text.dropLast(1)
                }
            }
            else -> {
                when(phoneNumber.length){
                    10 -> { }
                    3, 6 -> {
                        phoneNumberView.text = phoneNumberView.text.toString() + " " + button.tag
                        phoneNumber+=button.tag
                    }
                    else -> {
                        phoneNumberView.text = phoneNumberView.text.toString() + button.tag
                        phoneNumber+=button.tag
                    }
                }
            }
        }
        println(phoneNumber)
    }
}