package com.example.myapplicationcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationcalculadora.data.Calculadora
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
        var numeroAlmacenado: String = ""
        var auxNumero: String = ""
        var operador: String = ""

        btnSuma.setOnClickListener {

            tvView.setText(numeroAlmacenado.toString() + "+")
            operador = "+"
            auxNumero = numeroAlmacenado
            numeroAlmacenado = ""
            txtN1.setText("")

        }

        btnResta.setOnClickListener {
            tvView.setText(numeroAlmacenado.toString() + "-")
            operador = "-"
            auxNumero = numeroAlmacenado
            numeroAlmacenado = ""
            txtN1.setText("")
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

        btnIgual.setOnClickListener {
            if(operador == "+"){

                tvView.setText(tvView.text.toString() + numeroAlmacenado)
                val calculadora = Calculadora(numeroAlmacenado, auxNumero)
                tvResul.setText((calculadora.suma()))

            }else if( operador == "-"){
                tvView.setText(tvView.text.toString() + numeroAlmacenado)
                tvResul.setText((auxNumero.toInt() - numeroAlmacenado.toInt()).toString())
            }

            //borra datos o limpiar
            txtN1.setText("")
            auxNumero = ""
            numeroAlmacenado = ""
        }

        //alamenamiento de botnes
        btn1.setOnClickListener {
            numeroAlmacenado += "1"
            txtN1.setText(numeroAlmacenado)
        }

        btn2.setOnClickListener {
            numeroAlmacenado += "2"
            txtN1.setText(numeroAlmacenado)
        }

        btnComa.setOnClickListener {
            numeroAlmacenado += ","
            txtN1.setText(numeroAlmacenado)
        }

    }
    //funciones
    //procedimientos
}