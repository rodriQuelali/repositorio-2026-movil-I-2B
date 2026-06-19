package com.example.myapplicationcalculadora.data

//documentacion


/**
 * Calculadora, recibe 2 numeros, realiza las operaciones en formato
 * LATAM
 * @param numero1 primer numero
 * @param numero2 segundo numero
 * @version 1.0
 * @author Rodrigo
 * */
class Calculadora(
    private val numero1: String,
    private val numero2: String,
    private var formatoLatam: FormatoLatam = FormatoLatam())  {

    //analizar que pas si yo paso los valores de 2,4 + 4,5 = 6,9

    /**
     * Suma de 2 numeros, recibe 2 numeros en formato LATAM
     * @return resultado de la suma, en formato String
     * */
    fun suma():String{
        var n1 = formatoLatam.validadorComa(numero1)
        var n2 = formatoLatam.validadorComa(numero2)

        var s = n1.toFloat() + n2.toFloat()
        var s1 = formatoLatam.convertirDatos(s)

        return s1.toString()
    }


}