import java.time.LocalDate

abstract class Noticia(
    val tipo : String,
    val fechaEscrita: String,
    val importancia: Double,
    val titulo : String,
    val desarrollo : String){

    fun esCopada() = esCopadaBase() and esCopadaPart()
    fun esCopadaBase() = importancia>= 8.0 and (LocalDate.daysBetween(fechaEscritura, LocalDate.now())< 3)
    abstract fun esCopadaPart(): Boolean

    fun contiene(palabra : String) = desarrollo.contains(palabra)

    fun tituloEmpiezaCon(letra : Char) = titulo.first()==letra

    fun autorLaPrefiere() = periodista.loPrefiere(this)

    fun esGrande() = desarrollo.size() > 1000
}

class Chivo(
    val plataPagada : Double,
    fechaEscritura : LocalDate,
    periodista : Periodista,
    importancia: Double,
    tipo: String,
    desarrollo : String
    ) : Noticia(fechaEscritura, periodista, importancia, tipo, desarrollo){
        override fun esCopadaPart() = plataPagada > 200000.0
    }

class Reportaje(
    val entrevistado : String,
    tipo : String,
    val seDedicaAMusica : Boolean,
    periodista : Periodista,
    importancia : Double,
    titulo : String,
    desarrollo : String,
    fechaEscrita: LocalDate
    ): Noticia(fechaEscrita, tipo, periodista, importancia, titulo, desarrollo)
{
    override fun esCopadaPart() = entrevistado.size() == 25
}

class ArticuloComun(
    var listaLinks : List<String>,
    fechaEscrita: LocalDate,
    periodista, Periodista,
    tipo: String,
    importancia: Double,
    titulo : String,
    desarrollo: String): Noticia(fechaEscritura, periodista, importancia, titulo, desarrollo, tipo){

        override fun esCopadaPart() = listaLinks.size >= 2

    }