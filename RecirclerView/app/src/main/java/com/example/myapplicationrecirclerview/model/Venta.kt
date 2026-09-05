package com.example.myapplicationrecirclerview.model

class Venta (
    private val nombreProducto: String,
    private val nombreCliente: String,
    private val cantidad: Int,
    private val precio:Int
    //atributos restantes
){
    //metodos
    //get an set
    fun getNombreCliente():String = this.nombreCliente

    fun getNombreProducto():String = this.nombreProducto

    fun getCantidad(): Int = this.cantidad
}