import java.time.LocalDate

interface MailSender{
    fun sendMail(mail :String)
}

interface SitioWeb{
    fun recibirPublicacion(listaNoticias : ListOf<Noticias>, fecha: LocalDate)
}

data class Mail(from : String, to: String, subject: String, body: String)

interface AgenciaANSI{
    fun recibirLista(listaFormateada : String)
}