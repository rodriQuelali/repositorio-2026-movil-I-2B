package com.example.myapplicationcalculadora.data

class Calculadora(
    private val numero1: String,
    private val numero2: String,
    private var formatoLatam: FormatoLatam = FormatoLatam())  {

    //analizar que pas si yo paso los valores de 2,4 + 4,5 = 6,9
    fun suma():String{
        var n1 = formatoLatam.validadorComa(numero1)
        var n2 = formatoLatam.validadorComa(numero2)

        var s = n1.toFloat() + n2.toFloat()
        var s1 = formatoLatam.convertirDatos(s)

        return s1.toString()
    }


}