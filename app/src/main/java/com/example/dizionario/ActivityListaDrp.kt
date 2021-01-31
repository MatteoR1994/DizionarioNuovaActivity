package com.example.dizionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_drp.*

class ActivityListaDrp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drp)

        // UTILIZZANDO UN ARRAY FUNZIONA.
        val arrayDrp = intent.getStringArrayExtra("listaDRP")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayDrp!!)

        // UTILIZZANDO UNA STRINGA FUNZIONA.
        /*val parametro = intent.getStringExtra("listaDRP")
        val arrayDrp = (parametro!!.trim('&')).split('&')
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayDrp)*/

        listaDrp.adapter = adapter

        backButton.setOnClickListener {
            finish()
        }
    }
}