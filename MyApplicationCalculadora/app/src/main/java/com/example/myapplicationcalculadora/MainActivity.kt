package com.example.myapplicationcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        //conectar con clase R
        val txtN1 = findViewById<EditText>(R.id.txtNum1)
        val txtN2 = findViewById<EditText>(R.id.txtNum2)

        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMulti = findViewById<Button>(R.id.btnMultiplicacion)
        val btnDivision = findViewById<Button>(R.id.btnDivision)

        val tvResul = findViewById<TextView>(R.id.tvResultado)

        //programacion estructura
        //POO
        //Procxima clas es POO
        btnSuma.setOnClickListener {
            val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 + num2
            tvResul.text = resul.toString()
        }

        btnResta.setOnClickListener {
            val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 - num2
            tvResul.text = resul.toString()
        }

        btnMulti.setOnClickListener {
            val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 * num2
            tvResul.text = resul.toString()
        }

        //division, analizar division

    }
    //funciones
    //procedimientos
}