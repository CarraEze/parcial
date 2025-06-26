import java.time.LocalDate

class Periodista (val nombre : String, val fechaIngreso : LocalDate){
    lateinit var preferencia : Preferencia

    fun loPrefiere(noticia: Noticia) = preferencia.seCumple(noticia)

    fun recibirPago(monto : Double){}

    fun cambiarPreferencia(preferenciaN : Preferencia) {
        preferencia = preferenciaN
    }
}

interface Preferencia{
    fun seCumple(noticia : Noticia): Boolean
}

object noticiasCopadas: Preferencia{
    override fun seCumple(noticia: Noticia): Boolean = noticia.esCopada()
}

class NoticiasSencacionalistas : Preferencia{
    var palabrasSensacionales : List<String> = mutableListOf("espectacular", "increible", "grandioso")

    override fun seCumple(noticia: Noticia) : Boolean =
        noticia.cumpleSensacionalista(palabrasSensacionales)
}

object comienzaConT : Preferencia{
    override fun seCumple(noticia: Noticia): Boolean{
        return noticia.tituloEmpiezaCon('t')
    }
}
