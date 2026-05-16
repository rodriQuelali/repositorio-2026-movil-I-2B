fun main(){
    var administrativos:Administrativos=Administrativos()
    administrativos.caminando()

}

//class abtract -- no instabncia, peros hace la herencia
//se comportan tam,bien en interfaces

abstract class Persona6{
    //metodos
    abstract fun caminando()
}

class Administrativos(): Persona6(), Persona7{
    override fun caminando() {
        print("caminando administrada")
    }

    override fun correr() {
        print("caminando correr")
    }

    override fun comer() {
        print("caminando comer")
    }
}


interface Persona7{
    //lista metodos
    fun caminando()
    fun correr()
    fun comer()

}
