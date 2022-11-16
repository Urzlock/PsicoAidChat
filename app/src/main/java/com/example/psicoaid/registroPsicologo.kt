package com.example.psicoaid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.Spanned
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.util.regex.Pattern

class registroPsicologo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_psicologo)
        val adapter1 = ArrayAdapter.createFromResource(this,R.array.especialidades,android.R.layout.simple_spinner_item).also { arrayAdapter ->arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)  }
        val adapter2 = ArrayAdapter.createFromResource(this,R.array.sexo,android.R.layout.simple_spinner_item).also { arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)  }
        var textViewtxt: TextView = findViewById(R.id.txtEjemplo)
        val  textNombre : EditText = findViewById(R.id.textNombre)
        val textApellidos: EditText = findViewById(R.id.textApellidos)
        val textEdad : EditText= findViewById(R.id.textEdad)
        textEdad.filters = arrayOf<InputFilter>(LengthFilter(2))
        val textSexo : Spinner = findViewById(R.id.textSexo)
        val textEspecialidad= findViewById<Spinner>(R.id.textEspecialidad)
        val textNombreUser: EditText = findViewById(R.id.textNombreUser)
        val textTelefono : EditText = findViewById(R.id.textTelefono)
        val textCorreo : EditText = findViewById(R.id.textCorreo)
        val textPassword : EditText = findViewById(R.id.textPassword)
        val btnRegisterPsic: Button = findViewById(R.id.btnRegisterPsic)
        textEspecialidad.adapter= adapter1
        textSexo.adapter=adapter2

        var nombre:String
        var apellidos:String
        var edad:String
        var sexo:String
        var especialidad:String
        var nombreUser:String
        var telefono:String
        var correo:String
        var password:String

//INICIO DE CLASE QUE DETERMINA EL MINIMO Y MAXIMO DE DE EDAD

//FIN DE LA CLASE QUUE DETERMINA EL MINIMO Y MAXIMO DE EDAD
        btnRegisterPsic.setOnClickListener {
           if(textNombre.text.isNotEmpty() && textApellidos.text.isNotEmpty() && textEdad.text.isNotEmpty() && textNombreUser.text.isNotEmpty() && textTelefono.text.isNotEmpty() && textCorreo.text.isNotEmpty()  && textPassword.text.isNotEmpty()){
                nombre=textNombre.text.toString()
               apellidos=textApellidos.text.toString()
               edad=textEdad.text.toString()
               sexo=textSexo.selectedItem.toString()
               especialidad=textEspecialidad.selectedItem.toString()
               nombreUser=textNombreUser.text.toString()
               telefono=textTelefono.text.toString()
               correo=textCorreo.text.toString()
               password=textPassword.text.toString()
               Toast.makeText(this,"${sexo}, ${especialidad}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Por Favor Rellene todos los campos",Toast.LENGTH_LONG).show()
            }
        }

    }
    /* Validación de Edad---> Utilizar Expresiones regulares
    fun validarEdad(text: String?):Boolean{
        val p = Pattern.compile()
    }*/
}