package com.example.psicoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class conversacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversacion)
       val btnMsg : Button = findViewById(R.id.btnSendMsg)
        val txtMsg : EditText= findViewById(R.id.txtMsg)

        btnMsg.setOnClickListener {
            txtMsg.text=null
        }
    }
}