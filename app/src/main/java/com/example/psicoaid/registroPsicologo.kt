package com.example.psicoaid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class registroPsicologo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_psicologo)
        val adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.especialidades,
            android.R.layout.simple_spinner_item
        )
            .also { arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item) }
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.sexo,
            android.R.layout.simple_spinner_item
        )
            .also { arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item) }
        val textNombre: EditText = findViewById(R.id.textNombre)
        val textApellidos: EditText = findViewById(R.id.textApellidos)
        val textEdad: EditText = findViewById(R.id.textEdad)
        textEdad.filters = arrayOf<InputFilter>(LengthFilter(2))
        val textSexo: Spinner = findViewById(R.id.textSexo)
        val textEspecialidad = findViewById<Spinner>(R.id.textEspecialidad)
        val textNombreUser: EditText = findViewById(R.id.textNombreUser)
        val textTelefono: EditText = findViewById(R.id.textTelefono)
        val textCorreo: EditText = findViewById(R.id.textCorreo)
        val textPassword: EditText = findViewById(R.id.textPassword)
        val btnRegisterPsic: Button = findViewById(R.id.btnRegisterPsic)
        textEspecialidad.adapter = adapter1
        textSexo.adapter = adapter2

        var nombre: String
        var apellidos: String
        var edad: String
        var sexo: String
        var especialidad: String
        var nombreUser: String
        var telefono: String
        var correo: String
        var password: String


        btnRegisterPsic.setOnClickListener {
            if (textNombre.text.isNotEmpty() && textApellidos.text.isNotEmpty() && textEdad.text.isNotEmpty() && textNombreUser.text.isNotEmpty() && textTelefono.text.isNotEmpty() && textCorreo.text.isNotEmpty() && textPassword.text.isNotEmpty()) {
                nombre = textNombre.text.toString()
                apellidos = textApellidos.text.toString()
                edad = textEdad.text.toString()
                sexo = textSexo.selectedItem.toString()
                especialidad = textEspecialidad.selectedItem.toString()
                nombreUser = textNombreUser.text.toString()
                telefono = textTelefono.text.toString()
                correo = textCorreo.text.toString()
                password = textPassword.text.toString()
                if (validarPassword(password) == true) {
                    Toast.makeText(this, "Contrase??a valida", Toast.LENGTH_LONG).show()
                    val url="https://psicoaid.000webhostapp.com/ingreso.php?nombre=${nombre}&apellidos=${apellidos}&edad=${edad}&sexo=${sexo}&especialidad=${especialidad}&nombreUser=${nombreUser}&telefono=${telefono}&correo=${correo}&password=${password}"
                    val servicio = Volley.newRequestQueue(this)
                    val stringRequest = StringRequest(Request.Method.GET, url,
                        Response.Listener<String> { response ->
                            // Display the first 500 characters of the response string.

                        },
                        Response.ErrorListener {  })
                    servicio.add(stringRequest)

                }
                else{
                   Toast.makeText(this,"Contrase??a invalida,Ingrese 1 Numero, 1 Mayuscula y Minuscula, 1 Caracter Especial y sin espacios en 6 o mas caracteres",Toast.LENGTH_LONG).show()
               }
               Toast.makeText(this,"${sexo}, ${especialidad}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Por Favor Rellene todos los campos",Toast.LENGTH_LONG).show()
            }
        }

    }
    // Validaci??n de Contrase??a--> Por lo menos 1 Digito,1 minuscula, 1 mayuscula, 1 caracter especial y no espacios con una longitud de 6 minimo
    fun validarPassword(text: String?):Boolean{
        text?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(text) != null
        } ?: return false
    }

}