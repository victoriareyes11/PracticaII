package com.example.practicaii

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

     //private lateinit var correoR :String
     //private lateinit var contrasenaR :String
     private var correoR :String=" "
     private var  contrasenaR :String=" "
     private lateinit var correoL :String
     private lateinit var contrasenaL :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)



        tv_registro.setOnClickListener {
            val intent =Intent(this,RegistroActivity::class.java)
            intent.putExtra("correok","nombre.com")
            intent.putExtra("contrasenak","qwerty")
            startActivityForResult(intent,1234)

        }


         bt_enviar.setOnClickListener {
             correoL = et_correol.text.toString()
             contrasenaL = et_contrasenal.text.toString()



                //if(getCallingActivity()?.getClassName() == RegistroActivity::class.java.name) {

                  if (correoL == correoR && contrasenaL == contrasenaR) {
                     val intent = Intent(this, MainActivity::class.java)
                     intent.putExtra("correok", correoR)
                     startActivity(intent)
                      et_correol.getText().clear()
                      et_contrasenal.getText().clear()



                 } else {
                     Toast.makeText(this, "Informacion incorrecta", Toast.LENGTH_SHORT).show()
                 }
              // }
           /*    else{
                 Toast.makeText(this, "Usuario no se encuentra registrado", Toast.LENGTH_SHORT).show()
                 //finish()
             }*/
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null) {

            if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
                correoR = data!!.extras?.getString("correok").toString()
                contrasenaR = data!!.extras?.getString("contrasenak").toString()


            }
        }

    }
}
