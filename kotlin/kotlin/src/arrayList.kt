fun main(){

    //array y lista

    //array o vectores
    /*
    ByteArray
    ShortArray
    LongArray
    FloatArray
    DoubleArray = [true, true, false]
    BooleanArray
    CharArray
    */

    var sueldos: IntArray = intArrayOf(100,200,135,4000,5500)

    //verctor posicion[0, 1, 2, 3, ...]
    for (i in 0..4){
        println(sueldos[i])
    }

    sueldos.forEach {  }


    //listas
    var listaCurso = listOf<String>("1ro A", "2do A", "3do A")
    //listaCurso.add() //no de puede
    //listasCurso[4] = "1ero B" -- no se puede
    for(listaCurso in listaCurso){
        println(listaCurso)
    }
    listaCurso.forEach {
        println(it)
    }

    ///MutableList
    //es mutable, ose que cambia los valores.
    //MutableLis -- es un tipo de datos abstracto.
    //tipo de datos abstractos:Pilas, colas, lista enlazada.
    val edad : MutableList<Int> = mutableListOf(23,45)
    edad.add(23)
    edad.forEach { println(it) }
    println(edad)


}