package com.example.psicoaid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.psicoaid.databinding.ActivityPrincipalPsicologoBinding

class principalPsicologo : AppCompatActivity() {
    private lateinit var binding :ActivityPrincipalPsicologoBinding
    private  var id: String? = ""
    private  var userName: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityPrincipalPsicologoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
         id = bundle?.getString("idUser")
        userName= bundle?.getString("userName")
        replaceFragment(Home())
      Toast.makeText(this,bundle.toString(),Toast.LENGTH_LONG).show()
        binding.navView.setOnItemReselectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(Home())
                R.id.cuenta->replaceFragment(Cuenta())
                R.id.chat->replaceFragment(Chat())
                R.id.RegistrarHorario-> replaceFragment(Registro_Horario())
                else ->{

                }

            }
            true
        }
    }

    private fun replaceFragment(fragment:Fragment){
        val bundle1 = Bundle()
        bundle1.putString("idUser",id)
        bundle1.putString("userName",userName)
        fragment.arguments= bundle1
        val fragmentManager = supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}