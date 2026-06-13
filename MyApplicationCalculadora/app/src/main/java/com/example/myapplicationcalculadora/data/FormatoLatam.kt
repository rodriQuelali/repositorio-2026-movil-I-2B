package com.example.myapplicationcalculadora.data

class FormatoLatam {

    fun validadorComa(a:String):String{
        var n1 = a.replace(",",".")

        return n1
    }

    fun validadorPunto(a:String):String{
        var n1 = a.replace(".",",")

        return n1
    }

    fun convertirDatos(a:Float):Any{
        if(a % 1 == 0f){
            val x = a

            return x.toInt()
        }

        return validadorPunto(a.toString())
    }
}