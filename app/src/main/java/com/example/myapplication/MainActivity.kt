package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var phoneNumberView: TextView
    var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialer)
        phoneNumberView = findViewById(R.id.phoneNumberView)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> dial()
        }
    }

    private fun dial(){
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun addToContacts(){
        val intent = Intent(Intent.ACTION_INSERT).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber)
        }
        if (intent.resolveActivity(packageManager)!=null) {
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onClickListener(button: View){
        when(button.id){
            R.id.dialBtn -> {
                when {
                    ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CALL_PHONE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        // permission already granted
                        dial()
                    }
                    else -> {
                        // requesting permission
                        requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), 1)
                    }
                }
            }
            R.id.addToContactsBtn -> {
                addToContacts()
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