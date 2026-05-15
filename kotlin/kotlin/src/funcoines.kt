fun main(){
    val a: Int = suma(2,8)
    //println(a)

    //sumas(5,8)

    val lista = sumasMejoradaLista(mutableListOf(6,9,66,99,69,37,42,22,12,2,25,39,77,30,15,8,1), -10)
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
fun  sumasMejoradaLista(a: MutableList<Int>, b: Int): MutableList<Int>{
    //[12, 56,34]
    //17
    //[6,9,66,99,69,37,42,22,12,2,25,39,77,30,15,8,1]
    //-10
    //index[1]
    a.mapIndexed { index, i ->  a.set(index, i+b)}
    return a
}


//procedimiento

fun sumas(a:Int, b:Int){
    print(a+b)
}
