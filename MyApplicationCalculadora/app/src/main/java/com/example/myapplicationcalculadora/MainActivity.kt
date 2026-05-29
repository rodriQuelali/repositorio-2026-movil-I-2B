package com.example.myapplicationcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

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
        val txtN1 = findViewById<TextInputEditText>(R.id.txtValorOperar)
        val tvView = findViewById<TextView>(R.id.tvView)
        val tvResul = findViewById<TextView>(R.id.tvResultado)


        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMulti = findViewById<Button>(R.id.btnMultiplicacion)
        val btnDivision = findViewById<Button>(R.id.btnDivision)
        val btnIgual = findViewById<Button>(R.id.btnIgual)


        //numeros
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btnComa = findViewById<Button>(R.id.btn)

        //programacion estructura
        //POO
        //Procxima clas es POO
        btnSuma.setOnClickListener {
            /*val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 + num2
            tvResul.text = resul.toString()*/
        }

        btnResta.setOnClickListener {
            /*val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 - num2
            tvResul.text = resul.toString()*/
        }

        btnMulti.setOnClickListener {
            /*val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            val resul: Int = num1 * num2
            tvResul.text = resul.toString()*/
        }

        //division, analizar division
        btnDivision.setOnClickListener {
            /*val num1 = txtN1.text.toString().toInt()
            val num2 = txtN2.text.toString().toInt()
            if(num2 == 0){
                  tvResul.text = "No se puede dividir entre 0"
            }else{
                val resul: Int = num1 / num2
                tvResul.text = resul.toString()
            }*/
        }

    }
    //funciones
    //procedimientos
}