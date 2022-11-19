package com.example.psicoaid

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
//import com.cloudinary.android.MediaManager


class envioCertificado : AppCompatActivity() {
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
        var ola:String = "d"
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView:ImageView= findViewById(R.id.imageView2)
        if(requestCode==123){

            var bmp=data?.extras?.get("data")as Bitmap
            imageView.setImageBitmap(bmp)
        }
        else if(requestCode==456){
            imageView.setImageURI(data?.data)

        }
    }


}