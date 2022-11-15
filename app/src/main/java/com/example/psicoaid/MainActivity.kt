package com.example.psicoaid
//Este Activity esta planteado para ser el Inicio de Sesión
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRegistrarPsic : Button= findViewById(R.id.btnRegistroPsic)
        btnRegistrarPsic.setOnClickListener {
            startActivity(Intent(this,registroPsicologo::class.java))
        }
    }
}