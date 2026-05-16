fun main(){
    //new -- no ay
    /*var persona:Persona = Persona()
    persona.inicializar("alan brito", 23)
    persona.caminar()*/

    var persona:Persona3 = Persona3("alan brito", 23)
    //persona.caminar()

    var persona5 = Persona5("alan brito", 23)
    //persona5.setNombre("alan brito dos")
    //print(persona5.getNombre())

    var alumno: Alumno = Alumno("alan brito", 23, 61)
    alumno.caminar()


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

//otra forma de constructor
class Persona2 constructor(nombre:String, edad:Int) {
    var nombre: String = ""
    var edad: Int = 0

    fun caminar(){
        print("caminando persona 2")
    }
}

//otra forma de cobntructor, menos codigo
//para herencia
//open
open class Persona3 (var nombre:String, var edad:Int) {
    open fun caminar(){
        print("caminando persona 3")
    }
}

//encapsulamiento
class Persona4 (private var nombre:String, private var edad:Int) {
    private fun caminar(){
        print("caminando persona 3")
    }
}

//set and get
class Persona5 (private  var nombre:String, private var edad:Int) {
    //set an get
    fun setNombre(nombre: String){
        this.nombre = nombre
    }

    fun getNombre():String{
        return  this.nombre
    }

}

//herencia
//open = final
class Alumno(nombre: String, edad: Int, var nota:Int): Persona3(nombre, edad){

    //overide
    //polimorfismo
    override fun caminar(){
        //super.caminar()
        print("caminando alumno")
    }
}

