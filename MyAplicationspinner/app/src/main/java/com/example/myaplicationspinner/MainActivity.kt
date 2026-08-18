package com.example.myaplicationspinner

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //el codigo
        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.button)
        val tv1 = findViewById<TextView>(R.id.tv1)

        //lista de string , o vector: Suma, Resta, Multiplicacion, Division
        val lista = arrayOf("Suma", "Resta", "Multiplicacion", "Division")

        // el adaptador.
        val adaptador1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1

        button.setOnClickListener {
            when (spinner.selectedItem.toString()) {
                "Suma" -> tv1.text = "Resultado: ${(et1.text.toString().toInt() + et2.text.toString().toInt()).toString()}"
                "Resta" -> tv1.text = "Resultado: ${(et1.text.toString().toInt() - et2.text.toString().toInt()).toString()}"
                "Multiplicacion" -> tv1.text = "Resultado: ${(et1.text.toString().toInt() * et2.text.toString().toInt()).toString()}"
                "Division" -> tv1.text = "Resultado: ${(et1.text.toString().toInt() / et2.text.toString().toInt()).toString()}"
            }
        }
    }
}