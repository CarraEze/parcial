import java.time.LocalDate

class Periodista (val nombre : String, val fechaIngreso : LocalDate){
    lateinit var preferencia : Preferencia

    fun loPrefiere(noticia: Noticia) = preferencia.seCumple(noticia)

    fun recibirPago(monto : Double) = TODO()

    fun cambiarPreferencia(preferenciaN : Preferencia) {
        preferencia = preferenciaN
    }
}

interface Preferencia{
    fun seCumple(noticia : Noticia): Boolean
}

object noticiasCopadas(): Preferencia{
    override fun seCumple(noticia: Noticia) = noticia.esCopada()
}

object NoticiasSencacionalistas() : Preferencia{
    var palabrasSensacionales : List<String> = ("espectacular", "increible", "grandioso")

    override fun seCumple(noticia: Noticia) {
        if(noticia.tipo == reportaje){
            return palabrasSensacionales.any{noticia.contiene(it)} and noticias.entrevistado == "Dibu Martinez"
        }
        else{
            return palabrasSensacionales.any{noticia.contiene(it)}
        }
    }
}

object comienzaConT : Preferencia{
    override fun seCumple(noticia: Noticia){
        return noticia.tituloEmpiezaCon("t")
    }
}
