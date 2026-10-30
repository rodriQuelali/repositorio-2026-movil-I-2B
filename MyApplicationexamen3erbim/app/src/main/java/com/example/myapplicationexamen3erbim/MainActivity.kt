package com.example.myapplicationexamen3erbim

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationexamen3erbim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaNotasList = mutableListOf<Nota>()
        binding.crListN.layoutManager = LinearLayoutManager(this)

        binding.button.setOnClickListener {
            val nota: Nota = Nota(
                binding.txtN.text.toString(),
                binding.txtCi.text.toString().toInt(),
                binding.txtCar.text.toString(),
                binding.txtMat.text.toString(),
                binding.txtN1.text.toString().toDouble(),
                binding.txtN2.text.toString().toDouble(),
                binding.txtN3.text.toString().toDouble(),
                binding.txtN4.text.toString().toDouble(),

            )
            listaNotasList.add(nota)
            binding.crListN.adapter = AdapterNota(listaNotasList)
        }
    }
}