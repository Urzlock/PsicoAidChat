package com.example.psicoaid

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection


//import com.cloudinary.android.MediaManager


class envioCertificado : AppCompatActivity() {
    lateinit var bmp:Bitmap
    private var imgPath:Uri? =Uri.EMPTY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envio_certificado)
        val btnEnviar:Button = findViewById(R.id.btnEnviarImg)
        val btnGaleria:Button= findViewById(R.id.btnGaleria)
        val btnCapturar:Button= findViewById(R.id.btnCapturar)
        btnCapturar.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,123)
        }
        btnGaleria.setOnClickListener {
            val intent= Intent(Intent.ACTION_PICK)
            intent.type= "image/*"
            startActivityForResult(intent,456)
        }
        btnEnviar.setOnClickListener {


        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView:ImageView= findViewById(R.id.imageView2)

        if(requestCode==123){

            bmp=data?.extras?.get("data")as Bitmap
            imageView.setImageBitmap(bmp)
        }
        else if(requestCode==456){
            imageView.setImageURI(data?.data)

        }
    }

  }




