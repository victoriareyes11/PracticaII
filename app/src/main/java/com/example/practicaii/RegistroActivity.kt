package com.example.practicaii

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.practicaii.constantes.constantes.Companion.EMPTY
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String
    private lateinit var lugarnac : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_registro)

        val dataSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format ="MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha =sdf.format(cal.time).toString()
                tv_fechanac.text = fecha
            }

        tv_fechanac.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val spinner: Spinner = findViewById(R.id.sp_ciudad)
        ArrayAdapter.createFromResource(this, R.array.city_list, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }
        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        bt_registrarse.setOnClickListener {
            val nombre= et_nombre.text.toString()
            val correo: String= et_correo.text.toString()
            val contrasena:String= et_contrasena.text.toString()
            var sexo = EMPTY
            var pasatiempos = EMPTY


            lugarnac= spinner.selectedItem.toString()

            if(rb_masculino.isChecked)sexo= getString(R.string.masculino)
            else sexo=getString(R.string.femenino)

            if(ch_jugarf.isChecked)pasatiempos=pasatiempos + getString(R.string.jugar_futbol)
            if(ch_cine.isChecked)pasatiempos=pasatiempos + getString(R.string.cine)
            if(ch_pasearenbici.isChecked)pasatiempos=pasatiempos + getString(R.string.pasear_bici)
            if(ch_vertv.isChecked)pasatiempos=pasatiempos + getString(R.string.ver_tv)



                if (et_telefono.text.toString().isEmpty() || nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || contrasena.isEmpty() || sexo.isEmpty() || pasatiempos.isEmpty() || fecha.isEmpty()) {

                    Toast.makeText(this, "Debe diligenciar todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    if (isEmailValid(correo)) {
                        if (contrasena.length > 5) {
                            if (contrasena == et_recontrasena.text.toString()) {

                                val intent = Intent()
                                intent.putExtra("correok", correo)
                                intent.putExtra("contrasenak", contrasena)
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            Toast.makeText(
                                this,
                                "Contraseña debe tener minimo 6 digitos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                        else{
                            et_correo.getText().clear()
                            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show()

                        }


            }


        }
    }
}
