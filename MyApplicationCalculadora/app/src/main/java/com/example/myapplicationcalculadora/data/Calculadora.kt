package com.example.myapplicationcalculadora.data

class Calculadora(val numero1: Int, val numero2: Int)  {

    fun suma():Int{
        return this.numero1 + this.numero2
    }

    fun suma(): Float{
        return this.numero1.toFloat() + this.numero2.toFloat()
    }


    //una solucion sobre carga de metos, no necesito el constructor.
    //mantener todoo en String las funcioens de suma.-

    //analizar que pas si yo paso los valores de 2,4 +4,5 = 6,9

}