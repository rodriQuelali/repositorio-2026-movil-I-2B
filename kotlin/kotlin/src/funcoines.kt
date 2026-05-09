fun main(){
    val a: Int = suma(2,8)
    //println(a)

    //sumas(5,8)

    val lista = sumasLista(mutableListOf(12,34,6), 10)
    println(lista)
    //suma total = 52

    //utilizar sin axiliar de la mutableList.


}

//funciones
fun suma(a:Int, b:Int):Int{
 return a+b
}
//funcoine de una sola linea
fun restar(a:Int, b:Int):Int = a-b


fun sumasLista(a: MutableList<Int>, b: Int): MutableList<Int>{
    var bb:Int = 0
    var listaNueva: MutableList<Int> = mutableListOf()

    a.forEach {
        listaNueva.add(it+b)
         }
    return listaNueva
}


//procedimiento

fun sumas(a:Int, b:Int){
    print(a+b)
}
