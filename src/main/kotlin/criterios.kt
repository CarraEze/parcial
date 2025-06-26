import java.time.LocalDate

class ListaNoticias(var listado : MutableList<Noticia>, var sitioWeb : SitioWeb){
    lateinit var criterio : Criterio
    var listaObservers : MutableList<PublicacionObserver> = mutableListOf()
    fun cambiarCriterio(criterioN : Criterio){
        criterio = criterioN
    }

    fun filtrar(){
        listado.filter{!criterio.cumple(it)}
    }
    fun agregarNoticia(noticia : Noticia){listado.add(noticia)}
    fun eliminarNoticia(noticia : Noticia){listado.remove(noticia)}
    fun agregarObserver(observerN : PublicacionObserver){listaObservers.add(observerN)}
    fun eliminarObserver(observerN : PublicacionObserver){listaObservers.remove(observerN)}

    fun confirmarPublicacion(){
        sitioWeb.recibirPublicacion(listado, LocalDate.now().plusDays(1))
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
    override fun cumple(noticia: Noticia): Boolean{
        return NoticiasSencacionalistas().seCumple(noticia)
    }
}

class GradoImportancia(var min : Double, var max : Double) : Criterio{
    override fun cumple(noticia: Noticia) = (min<=noticia.importancia && noticia.importancia<=max)
}

class CriterioCombinado(val criterios : List<Criterio>) : Criterio {
    override fun cumple(noticia: Noticia) = criterios.all{it.cumple(noticia)}
}