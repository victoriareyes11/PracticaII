package com.example.practicaii

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datosrecibidos = this.intent.extras
        tv_correoM.text= datosrecibidos?.getString("correok").toString()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_cerrarsesion){
            val intent= Intent(this,LoginActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)

            finish()


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
    }
}
