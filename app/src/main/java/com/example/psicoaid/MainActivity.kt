package com.example.psicoaid
//Este Activity esta planteado para ser el Inicio de Sesión

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject




class MainActivity : AppCompatActivity() {

    var context = this
    var connectivity : ConnectivityManager?=null
    var info: NetworkInfo?=null
    lateinit var requestQueue:RequestQueue

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtRegistrarPaciente: TextView= findViewById(R.id.txtRegistro_Paciente)
        val txtRegistrarPsicologo: TextView= findViewById(R.id.txtRegistro_Psicologo)
        val textUser:EditText= findViewById(R.id.txtLoginUser)
        val textPassword:EditText=findViewById(R.id.txtLoginPassword)
        val btnCap:Button =findViewById(R.id.btnCap)
        val btnLogin:Button = findViewById(R.id.btnLogin)
        var user :String =textUser.text.toString()
        var password:String = textPassword.text.toString()
        val queue= Volley.newRequestQueue(this)

        txtRegistrarPsicologo.setOnClickListener {
            startActivity(Intent(this,registroPsicologo::class.java))
        }
        btnCap.setOnClickListener {
            startActivity(Intent(this,envioCertificado::class.java))
        }
        txtRegistrarPaciente.setOnClickListener {
            startActivity(Intent(this,registroPaciente::class.java))
        }

        btnLogin.setOnClickListener {



                var url: String = "https://psicoaid.000webhostapp.com/buscar_usuario.php?userName=${textUser.text}&password=${textPassword.text}"
                connectivity = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager


                        val servicio: RequestQueue = Volley.newRequestQueue(this)
                        val respuesta = StringRequest(
                            Request.Method.GET, url,
                            { response ->

                                try {
                                    val jArray = JSONArray(response)
                                    for (i in 0 until jArray.length()) {
                                        var jObject = jArray.getJSONObject(i)
                                        var ID: String = jObject.get("idUsuario").toString()
                                        var userName:String = jObject.get("Usuario").toString()
                                        //Toast.makeText(this,  jObject.get("NombreUsuario").toString(),Toast.LENGTH_LONG).show()
                                        var intent = Intent(this, principalPsicologo::class.java)
                                        var bundle = Bundle()
                                        bundle.putString("idUser", ID)
                                        bundle.putString("userName",userName)
                                        //Toast.makeText(this,bundle.toString(),Toast.LENGTH_LONG).show()
                                        intent.putExtras(bundle)
                                        startActivity(intent)


                                    }

                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                    Toast.makeText(this,"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show()
                                }
                            }) {


                        }
                        servicio.add(respuesta)
                        url="https://psicoaid.000webhostapp.com/buscar_paciente.php?userName=${textUser.text}&password=${textPassword.text}"
                        val servicio2:RequestQueue=Volley.newRequestQueue(this)
                        val respuesta2 =StringRequest(
                            Request.Method.GET, url,
                            { response ->

                                try {
                                    val jArray = JSONArray(response)
                                    for (i in 0 until jArray.length()) {
                                        var jObject2 = jArray.getJSONObject(i)
                                        var ID2: String = jObject2.get("idUsuario").toString()
                                        var userName:String = jObject2.get("Usuario").toString()
                                        //Toast.makeText(this,  jObject.get("NombreUsuario").toString(),Toast.LENGTH_LONG).show()
                                        var intent2 = Intent(this, principalPaciente::class.java)
                                        var bundle2 = Bundle()
                                        bundle2.putString("idUser", ID2)
                                        bundle2.putString("userName",userName)
                                        //Toast.makeText(this,bundle.toString(),Toast.LENGTH_LONG).show()
                                        intent2.putExtras(bundle2)
                                        startActivity(intent2)


                                    }

                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                   // Toast.makeText(this,"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show()
                                }
                            }) {
                            Toast.makeText(this,"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show()
                        }
                    servicio2.add(respuesta2)
                   //




            }

        }
    }


