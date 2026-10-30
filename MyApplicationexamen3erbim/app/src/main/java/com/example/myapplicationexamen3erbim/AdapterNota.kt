package com.example.myapplicationexamen3erbim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class AdapterNota(
    private val listaNotas:List<Nota>
): RecyclerView.Adapter<AdapterNota.NotaViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotaViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notas,parent,false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotaViewHolder,
        position: Int
    ) {
        val notas = listaNotas[position]
        holder.tvN.text = notas.nombre
        holder.tvN.text = "${notas.n1.toString()} - ${notas.optenerNota(notas.n1)}"
        holder.tvN.text = "${notas.n2.toString()} - ${notas.optenerNota(notas.n2)}"
        holder.tvN.text = "${notas.n3.toString()} - ${notas.optenerNota(notas.n3)}"
        holder.tvN.text = "${notas.n4.toString()} - ${notas.optenerNota(notas.n4)}"
        holder.tvN.text = "${notas.promedio()} - ${notas.optenerNota(notas.promedio())}"
    }

    override fun getItemCount(): Int = listaNotas.size

    class NotaViewHolder(itenView: View): RecyclerView.ViewHolder(itenView){
        val tvN = itenView.findViewById<TextView>(R.id.tvN)
        val tvCi = itenView.findViewById<TextView>(R.id.tvCI)
        val tvN1 = itenView.findViewById<TextView>(R.id.tvN1)
        val tv2 = itenView.findViewById<TextView>(R.id.tvN2)
        val tv3 = itenView.findViewById<TextView>(R.id.tvN3)
        val tvN4 = itenView.findViewById<TextView>(R.id.tvN4)
        val tvP = itenView.findViewById<TextView>(R.id.tvP)
    }
}