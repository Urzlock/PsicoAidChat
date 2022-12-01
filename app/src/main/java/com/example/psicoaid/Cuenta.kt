package com.example.psicoaid

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Cuenta.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cuenta : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(context,"Cargando información, Por Favor espere...",Toast.LENGTH_LONG).show()
        val view:View =inflater.inflate(R.layout.fragment_cuenta, container, false)
        var bundle: Bundle? = arguments
        var arrayAdapter:ArrayAdapter<*>

        if(bundle!=null){

            var idUser:String?=bundle?.getString("idUser").toString()
            var userName:String?= bundle.getString("userName")
            val lv:ListView=view.findViewById(R.id.lvDatos)
            var url: String = "https://psicoaid.000webhostapp.com/listar_datos_psicologo.php?userName=${userName}"
            val servicio: RequestQueue = Volley.newRequestQueue(context)
            val respuesta = StringRequest(
                Request.Method.GET, url,
                { response ->

                    try {
                        val jArray = JSONArray(response)
                        for (i in 0 until jArray.length()) {
                            var jObject = jArray.getJSONObject(i)

                            var userName:String = jObject.get("Usuario").toString()
                            var password:String =jObject.get("Password").toString()
                            var nombre:String = jObject.get("Nombre").toString()
                            var apellidos:String= jObject.get("Apellidos").toString()
                            var telefono:String=jObject.get("Telefono").toString()
                            var correo:String=jObject.get("Correo").toString()
                            var sexo:String= jObject.get("Sexo").toString()
                            var especialidad:String = jObject.get("Especialidad").toString()
                            var edad:String=jObject.get("Edad").toString()

                            var userNameTemp:String="Nombre de Usuario\n${userName}"
                            var passwordTemp:String="Contraseña\n${password}"
                            var nombreTemp:String="Nombre(s)\n${nombre}"
                            var apellidosTemp:String="Apellido(s)\n${apellidos}"
                            var telefonoTemp:String="Teléfono\n${telefono}"
                            var correoTemp:String="Correo\n${correo}"
                            var sexoTemp:String="Sexo\n${sexo}"
                            var especialidadTemp:String="Especialidad\n${especialidad}"
                            var edadTemp:String="Edad\n${edad}"
                            val datos= mutableListOf(nombreTemp,apellidosTemp,userNameTemp,correoTemp,passwordTemp,telefonoTemp,sexoTemp,edadTemp,especialidadTemp)
                            arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,datos)
                            lv.adapter=arrayAdapter

                            lv.setOnItemClickListener { adapterView, view, i, l ->
                                //datos.get(i)
                                val builder= AlertDialog.Builder(context)
                                val inflater =layoutInflater
                                val dialogLayout= inflater.inflate(R.layout.edit_text_layout,null)
                                val editText:EditText=dialogLayout.findViewById<EditText>(R.id.editDatos)
                                with(builder){
                                    setTitle("Editar ${datos.get(i)}")
                                    setPositiveButton("Aceptar"){dialog, which->
                                        Toast.makeText(context,editText.text.toString(),Toast.LENGTH_LONG).show()
                                    }
                                    setNegativeButton("Cancelar"){dialog, which->
                                        Toast.makeText(context,"Se cancelo la edición",Toast.LENGTH_LONG).show()
                                    }
                                    setView(dialogLayout)
                                    show()
                                }
                            }

                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }) {


            }
            servicio.add(respuesta)
            url="https://psicoaid.000webhostapp.com/listar_datos_paciente.php?userName=${userName}"
            val servicio1: RequestQueue = Volley.newRequestQueue(context)
            val respuesta1 = StringRequest(
                Request.Method.GET, url,
                { response ->

                    try {
                        val jArray = JSONArray(response)
                        for (i in 0 until jArray.length()) {
                            var jObject = jArray.getJSONObject(i)

                            var userName:String = jObject.get("Usuario").toString()
                            var password:String =jObject.get("Password").toString()
                            var nombre:String = jObject.get("Nombre").toString()
                            var apellidos:String= jObject.get("Apellidos").toString()
                            var telefono:String=jObject.get("Telefono").toString()
                            var correo:String=jObject.get("Correo").toString()
                            var sexo:String= jObject.get("Sexo").toString()
                           // var especialidad:String = jObject.get("Especialidad").toString()
                            var edad:String=jObject.get("Edad").toString()

                            var userNameTemp:String="Nombre de Usuario\n${userName}"
                            var passwordTemp:String="Contraseña\n${password}"
                            var nombreTemp:String="Nombre(s)\n${nombre}"
                            var apellidosTemp:String="Apellido(s)\n${apellidos}"
                            var telefonoTemp:String="Teléfono\n${telefono}"
                            var correoTemp:String="Correo\n${correo}"
                            var sexoTemp:String="Sexo\n${sexo}"
                            //var especialidadTemp:String="Especialidad\n${especialidad}"
                            var edadTemp:String="Edad\n${edad}"
                            val datos= mutableListOf(nombreTemp,apellidosTemp,userNameTemp,correoTemp,passwordTemp,telefonoTemp,sexoTemp,edadTemp)
                            arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,datos)
                            lv.adapter=arrayAdapter

                            lv.setOnItemClickListener { adapterView, view, i, l ->
                                //datos.get(i)
                                val builder= AlertDialog.Builder(context)
                                val inflater =layoutInflater
                                val dialogLayout= inflater.inflate(R.layout.edit_text_layout,null)
                                val editText:EditText=dialogLayout.findViewById<EditText>(R.id.editDatos)
                                with(builder){
                                    setTitle("Editar ${datos.get(i)}")
                                    setPositiveButton("Aceptar"){dialog, which->
                                        Toast.makeText(context,editText.text.toString(),Toast.LENGTH_LONG).show()
                                    }
                                    setNegativeButton("Cancelar"){dialog, which->
                                        Toast.makeText(context,"Se cancelo la edición",Toast.LENGTH_LONG).show()
                                    }
                                    setView(dialogLayout)
                                    show()
                                }
                            }

                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }) {
                    Toast.makeText(context,"Error al cargar los datos",Toast.LENGTH_LONG).show()
            }
            servicio1.add(respuesta1)

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Cuenta.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cuenta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}