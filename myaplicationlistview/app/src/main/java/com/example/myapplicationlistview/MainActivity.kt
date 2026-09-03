package com.example.myapplicationlistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationlistview.data.Pais

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


        //codigo

        val tv1=findViewById<TextView>(R.id.tv1)
        val list1=findViewById<ListView>(R.id.list1)
        val txtNombre = findViewById<EditText>(R.id.txtPais)
        val btnAgre = findViewById<Button>(R.id.btnAgregar)
        val txtHabiatntes = findViewById<EditText>(R.id.txtPais)

        val objectPais: MutableList<String> = mutableListOf()
        val objectHabitantes: MutableList<Int> = mutableListOf()
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objectPais)
        btnAgre.setOnClickListener {

            val p = Pais(txtNombre.text.toString(),txtHabiatntes.text.toString().toInt())
            //pop, push
            objectPais.add(p.getNombre())

            //add de habitantes
            objectHabitantes.add(p.getHabitantes())
            list1.adapter = adaptador1
            txtNombre.setText("")
        }



        list1.adapter = adaptador1
        list1.setOnItemClickListener { adapterView, view, i, l ->
            tv1.text = "Población de: ${objectHabitantes[i]}"
        }
    }
}