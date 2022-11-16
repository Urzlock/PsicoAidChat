package com.example.psicoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registroPsicologo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_psicologo)

        val  textNombre : EditText = findViewById(R.id.textNombre)
        val textApellidos: EditText = findViewById(R.id.textApellidos)
        val textEdad : EditText= findViewById(R.id.textEdad)
        //Agregar el combobox de SEXO
        val textNombreUser: EditText = findViewById(R.id.textNombreUser)
        val textTelefono : EditText = findViewById(R.id.textTelefono)
        val textCorreo : EditText = findViewById(R.id.textCorreo)
        val textPassword : EditText = findViewById(R.id.textPassword)
        val btnRegisterPsic: Button = findViewById(R.id.btnRegisterPsic)


        btnRegisterPsic.setOnClickListener {

            var nombre:String = textNombre.text.toString()
            var apellidos:String = textApellidos.text.toString()
            Toast.makeText(this,nombre,Toast.LENGTH_SHORT).show()
        }

    }
}