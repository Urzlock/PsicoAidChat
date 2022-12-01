package com.example.psicoaid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
 * Use the [Chat.newInstance] factory method to
 * create an instance of this fragment.
 */
class Chat : Fragment() {
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
//
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(context,"Cargando Mensajes, Por Favor Espere...", Toast.LENGTH_LONG).show()
        val view:View =inflater.inflate(R.layout.fragment_chat, container, false)
        var bundle: Bundle? = arguments
        var arrayAdapter: ArrayAdapter<*>
        Toast.makeText(context,bundle.toString(),Toast.LENGTH_LONG).show()
        if(bundle!=null){
           // Toast.makeText(context,bundle.getString("userName"),Toast.LENGTH_LONG).show()
            var idUser:String?=bundle.getString("idUser")
            var userName:String?= bundle.getString("userName")
            val lv: ListView =view.findViewById(R.id.lvChats)
            var url: String = "https://psicoaid.000webhostapp.com/listar_chats.php?userID=${idUser}"
            val listaMensajes = ArrayList<String>()
            val servicio: RequestQueue = Volley.newRequestQueue(context)
            val respuesta = StringRequest(
                Request.Method.GET, url,
                { response ->
                    //Toast.makeText(context,"Hasta aqui funciona",Toast.LENGTH_LONG).show()
                    try {
                        val jArray = JSONArray(response)


                        for (i in 0 until jArray.length()) {
                            var jObject = jArray.getJSONObject(i)

                            var userName:String = jObject.get("Usuario").toString()
                            //Toast.makeText(context,userName,Toast.LENGTH_LONG).show()
                            //val datos= mutableListOf(userName)
                            listaMensajes.add(userName)
                            arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,listaMensajes)
                            lv.adapter=arrayAdapter

                            lv.setOnItemClickListener { adapterView, view, i, l ->
                                var intent = Intent(context, conversacion::class.java)
                                var bundle = Bundle()
                                bundle.putString("idUser", idUser)
                                bundle.putString("userName",userName)
                                //Toast.makeText(this,bundle.toString(),Toast.LENGTH_LONG).show()
                                intent.putExtras(bundle)
                                startActivity(intent)
                            }

                        }


                    } catch (e: JSONException) {
                        Toast.makeText(context,"SUKA BLYAT 1", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }) {
                Toast.makeText(context,"SUKA BLYAT", Toast.LENGTH_LONG).show()

            }
            servicio.add(respuesta)

        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Chat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Chat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}