package com.example.myapplicationrecirclerview.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationrecirclerview.R
import com.example.myapplicationrecirclerview.model.Venta

class VentaAdapter(
    private val listVenta:List<Venta>
): RecyclerView.Adapter<VentaAdapter.VentaViewHolder>() {

    //inflamos el layaouts
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VentaViewHolder {
        //tengo que llamar a mi layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ventas, parent, false)
        return VentaViewHolder(view)
    }

    //operaciones con los objetos de View.
    override fun onBindViewHolder(
        holder: VentaViewHolder,
        position: Int
    ) {

        val nombreCliente = listVenta[position]
        holder.tvN.text = nombreCliente.getNombreCliente()
        holder.tvNP.text = nombreCliente.getNombreProducto()
        holder.tvCan.text = nombreCliente.getCantidad().toString()

    }

    //cantidad de datos para la lista de RecirclerView.
    override fun getItemCount(): Int = listVenta.size


    class VentaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //optener los ID de mi View o UI.
        val tvN = itemView.findViewById<TextView>(R.id.tvNombreCliente)
        val tvNP = itemView.findViewById<TextView>(R.id.tvNombreProducto)
        val tvCan = itemView.findViewById<TextView>(R.id.tvCantidad)
    }


}