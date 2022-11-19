package com.example.psicoaid
//Este Activity esta planteado para ser el Inicio de Sesión

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        val button:Button= findViewById(R.id.button)
        val textUser:EditText= findViewById(R.id.txtLoginUser)
        val textPassword:EditText=findViewById(R.id.txtLoginPassword)
        val btnPS :Button =findViewById(R.id.btnPS)
        val btnCap:Button =findViewById(R.id.btnCap)
        val btnRegistrarPsic : Button= findViewById(R.id.btnRegistroPsic)
        val btnLogin:Button = findViewById(R.id.btnLogin)
        var user :String =textUser.text.toString()
        var password:String = textPassword.text.toString()
        val queue= Volley.newRequestQueue(this)
        button.setOnClickListener {
            Toast.makeText(this,"${textUser.text}  ${textPassword.text}",Toast.LENGTH_LONG).show()
        }
        btnRegistrarPsic.setOnClickListener {
            startActivity(Intent(this,registroPsicologo::class.java))
        }
        btnCap.setOnClickListener {
            startActivity(Intent(this,envioCertificado::class.java))
        }
        btnPS.setOnClickListener {
            startActivity(Intent(this,principalPsicologo::class.java))
        }

        btnLogin.setOnClickListener {



                var url: String =
                    "http://192.168.1.130:80/PsicoAid/buscar_usuario.php?userName=${textUser.text}&password=${textPassword.text}"
                connectivity = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager


                        val servicio: RequestQueue = Volley.newRequestQueue(this)
                        val respuesta = StringRequest(
                            Request.Method.GET, url,
                            { response ->

                                try {
                                    val jArray = JSONArray(response)
                                    for (i in 0 until jArray.length()) {
                                        var jObject = jArray.getJSONObject(i)
                                        var ID: String = jObject.get("id").toString()
                                        var userName:String = jObject.get("NombreUsuario").toString()
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
                                }
                            }) {
                            Toast.makeText(
                                applicationContext,
                                "Error comunicación", Toast.LENGTH_SHORT
                            ).show()
                        }
                        servicio.add(respuesta)



            }

        }
    }


