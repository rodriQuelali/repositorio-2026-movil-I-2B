fun main(){
    //new -- no ay
    /*var persona:Persona = Persona()
    persona.inicializar("alan brito", 23)
    persona.caminar()*/

    var persona:Persona3 = Persona3("alan brito", 23)
    persona.caminar()

}


//clases
class Persona{
    //atributos
    var nombre: String = ""
    var edad: Int = 0

    //contructor
    fun inicializar(nombre:String, edad:Int){
        this.nombre = nombre
        this.edad = edad
    }

    fun caminar(){
        print("caminando")
    }

}

//otra forma de cobntructor
class Persona2 constructor(nombre:String, edad:Int) {
    var nombre: String = ""
    var edad: Int = 0

    fun caminar(){
        print("caminando persona 2")
    }
}

//otra forma de cobntructor, menos codigo
class Persona3 (var nombre:String, var edad:Int) {
    fun caminar(){
        print("caminando persona 3")
    }
}

//encapsulamiento
class Persona4 (private var nombre:String, private var edad:Int) {
    private fun caminar(){
        print("caminando persona 3")
    }
}