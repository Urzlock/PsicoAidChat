package com.example.psicoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class registroPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_paciente)

        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.sexo,
            android.R.layout.simple_spinner_item
        )
            .also { arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item) }
        val textNombre: EditText = findViewById(R.id.textNombrePaciente)
        val textApellidos: EditText = findViewById(R.id.textNombrePaciente)
        val textEdad: EditText = findViewById(R.id.textEdadPaciente)
        textEdad.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(2))
        val textSexo: Spinner = findViewById(R.id.textSexoPaciente)

        val textNombreUser: EditText = findViewById(R.id.textNombreUserPaciente)
        val textTelefono: EditText = findViewById(R.id.textTelefonoPaciente)
        val textCorreo: EditText = findViewById(R.id.textCorreoPaciente)
        val textPassword: EditText = findViewById(R.id.textPasswordPaciente)
        val btnRegisterPsic: Button = findViewById(R.id.btnRegisterPaciente)

        textSexo.adapter = adapter2

        var nombre: String
        var apellidos: String
        var edad: String
        var sexo: String
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
                nombreUser = textNombreUser.text.toString()
                telefono = textTelefono.text.toString()
                correo = textCorreo.text.toString()
                password = textPassword.text.toString()
                if (validarPassword(password) == true) {
                    Toast.makeText(this, "Contraseña valida", Toast.LENGTH_LONG).show()
                    val url="https://psicoaid.000webhostapp.com/ingreso_paciente.php?nombre=${nombre}&apellidos=${apellidos}&edad=${edad}&sexo=${sexo}&nombreUser=${nombreUser}&telefono=${telefono}&correo=${correo}&password=${password}"
                    val servicio = Volley.newRequestQueue(this)
                    val stringRequest = StringRequest(
                        Request.Method.GET, url,
                        Response.Listener<String> { response ->
                            // Display the first 500 characters of the response string.

                        },
                        Response.ErrorListener { })
                    servicio.add(stringRequest)

                }
                else{
                    Toast.makeText(this,"Contraseña invalida,Ingrese 1 Numero, 1 Mayuscula y Minuscula, 1 Caracter Especial y sin espacios en 6 o mas caracteres",
                        Toast.LENGTH_LONG).show()
                }

            }
            else{
                Toast.makeText(this,"Por Favor Rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun validarPassword(text: String?):Boolean{
        text?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(text) != null
        } ?: return false
    }
}