package com.example.myapplicationexamen3erbim

class Nota(
    val nombre:String,
    val ci: Int,
    private val carrera: String,
    private val materia: String,
    val n1: Double,
    val n2: Double,
    val n3: Double,
    val n4: Double,
) {

    fun optenerNota(n1: Double): String{
        return if(n1 >= 61) "aprobado " else "reporbado"
    }

    fun promedio (): Double{
        return (n1 + n2 + n3 + n4) / 4
    }
}