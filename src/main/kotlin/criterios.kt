import java.time.LocalDate

class ListaNoticias(var listado : MutableList<Noticia>){
    lateinit var criterio : Criterio
    var listaObservers : MutableList<Observer> = mutableListOf()
    fun cambiarCriterio(criterioN : Criterio){
        criterio = criterioN
    }

    fun filtrar(){
        listado.remove{!criterio.cumple(it)}
    }
    fun agregarNoticia(noticia : Noticia){listado.add(noticia)}
    fun eliminarNoticia(noticia : Noticia){listado.remove(noticia)}
    fun agregarObserver(observerN : Observer){listaObservers.add(observerN)}
    fun eliminarObserver(observerN : Observer){listaObservers.remove(observerN)}

    fun confirmarPublicacion(){
        sitioWeb.recibirPublicacion(listado, LocalDate.nextDay(LocalDate.now()))
        listaObservers.forEach{it.publicacionRealizada(listado)}
    }

}

interface Criterio {
    fun cumple(noticia : Noticia) : Boolean
}

class QueLeGuste() : Criterio{
    override fun cumple(noticia: Noticia) = noticia.autorLaPrefiere()
    }

class SeaSensacionalista() : Criterio{
    override fun cumple(noticia: Noticia){
        return NoticiasSensacionalistas().seCumple(noticia)
    }
}

class GradoImportancia(var min : Double, var max : Double) : Criterio{
    override fun cumple(noticia: Noticia) = (min<=noticia.importancia and noticia.importancia<=max)
}

class CriterioCombinado(val criterios : ListOf<Criterio>) : Criterio {
    override fun cumple(noticia: Noticia) = criterios.all{it.cumple(noticia)}
}