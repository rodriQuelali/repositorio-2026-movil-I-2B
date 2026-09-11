package com.example.myapplicationrecirclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationrecirclerview.databinding.ActivityMainBinding
import com.example.myapplicationrecirclerview.model.Venta
import com.example.myapplicationrecirclerview.viewmodel.VentaAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //configuracion de binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //una lista de datos, lista de objetos de ventas.

        val listaObjetVentas = mutableListOf<Venta>(
            Venta("laptop", "pepe", 5, 1500) ,
            Venta("Monitor", "pepe", 5, 1500)
            //mas datos de ventas
        )

        //inicalizar recicrclerView
        binding.rclVentas.layoutManager = LinearLayoutManager(this)

        //inicializae el adaptador
        val ventaAdapter = VentaAdapter(listaObjetVentas)

        //asignamos el dapatador a recircler view
        binding.rclVentas.adapter = ventaAdapter



        //codigo
    }
}