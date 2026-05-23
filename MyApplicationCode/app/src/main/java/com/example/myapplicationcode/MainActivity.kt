package com.example.myapplicationcode

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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


        //empiezo hacer codigo

        //conectar a mi boton y caja de texto
        // de la clase R
        //casting
        val btMensaje = findViewById<Button>(R.id.btnMensaje)


        //crando lavariable especifico
        val txtMen: EditText = findViewById(R.id.txtMensaje)
        //todos los Editext devuelven tipo dato  String. no Int, loat, Double,Bool
        // txtMen = "1"
        //cuiado en la aignacion de valor.
        //val txt = binding.txtMensaje
        //val txtNombre = findViewById<EditText>(R.id.txtMensaje)


        //evento click
        btMensaje.setOnClickListener {
            //alert()--javascript
            //Toast
            val mensaje: String = txtMen.text.toString()
            Toast.makeText(this, "hola mundo $mensaje", Toast.LENGTH_SHORT).show()

        }


    }
}