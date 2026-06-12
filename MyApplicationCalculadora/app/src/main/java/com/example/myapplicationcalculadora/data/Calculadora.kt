package com.example.myapplicationcalculadora.data

class Calculadora(val numero1: String, val numero2: String)  {

    //analizar que pas si yo paso los valores de 2,4 + 4,5 = 6,9
    fun suma():String{
        var n1 = numero1.replace(",",".")
        //n1 = 2.4
        var n2 = numero2.replace(",",".")
        var s = n1.toFloat() + n2.toFloat()
        var s1 = s.toString().replace(".",",")
        return s1
    }

    /*fun suma(): Float{
        return this.numero1.toFloat() + this.numero2.toFloat()
    }*/


    //una solucion sobre carga de metos, no necesito el constructor.
    //mantener todoo en String las funcioens de suma.-

    //analizar que pas si yo paso los valores de 2,4 +4,5 = 6,9
    // 2.5 +4.5 = 6.9

}