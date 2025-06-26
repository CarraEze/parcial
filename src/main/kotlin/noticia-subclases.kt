import java.time.LocalDate
import java.time.temporal.ChronoUnit

abstract class Noticia(
    val tipo : String,
    val periodista: Periodista,
    val fechaEscritura: LocalDate,
    val importancia: Double,
    val titulo : String,
    val desarrollo : String){

    fun esCopada() = esCopadaBase() and esCopadaPart()
    fun esCopadaBase() = importancia>= 8.0 && (ChronoUnit.DAYS.between(fechaEscritura, LocalDate.now())< 3)
    abstract fun esCopadaPart(): Boolean

    open fun cumpleSensacionalista(listaPalabras : List<String>):Boolean =
        listaPalabras.any{desarrollo.contains(it)}

    fun contiene(palabra : String) = desarrollo.contains(palabra)

    fun tituloEmpiezaCon(letra : Char) = titulo.first()==letra

    fun autorLaPrefiere() = periodista.loPrefiere(this)

    fun esGrande() = desarrollo.split("").size > 1000
}

class Chivo(
    val plataPagada : Double,
    fechaEscritura : LocalDate,
    periodista : Periodista,
    importancia: Double,
    tipo: String,
    titulo: String,
    desarrollo : String
    ) : Noticia( tipo,  periodista, fechaEscritura, importancia, titulo, desarrollo){
        override fun esCopadaPart() = plataPagada > 200000.0

    }

class Reportaje(
    val entrevistado : String,
    val seDedicaAMusica : Boolean,
    fechaEscritura : LocalDate,
    periodista : Periodista,
    importancia: Double,
    tipo: String,
    titulo: String,
    desarrollo : String
) : Noticia( tipo,  periodista, fechaEscritura, importancia, titulo, desarrollo){
    override fun esCopadaPart() = entrevistado.length == 25

    override fun cumpleSensacionalista(listaPalabras: List<String>): Boolean{
        return super.cumpleSensacionalista(listaPalabras) && entrevistado == "Dibu Martinez"
    }
}

class ArticuloComun(
    var listaLinks : List<String>,
    fechaEscritura : LocalDate,
    periodista : Periodista,
    importancia: Double,
    tipo: String,
    titulo: String,
    desarrollo : String
) : Noticia( tipo,  periodista, fechaEscritura, importancia, titulo, desarrollo){
        override fun esCopadaPart() = listaLinks.size >= 2
    }