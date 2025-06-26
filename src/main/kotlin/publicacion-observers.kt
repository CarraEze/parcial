interface PublicacionObserver{
    fun publicacionRealizada(listado : MutableList<Noticia>)
}

class Pagar() : PublicacionObserver {
    override fun publicacionRealizada(listado : MutableList<Noticia>) {
        listado.forEach {
            if(it.esGrande()){
                it.periodista.recibirPago(75000.0)
            }else{
                it.periodista.recibirPago(50000.0)
            }
        }
    }
}

class Notificar(mailD : String, mailService : MailSender){
    override fun publicacionRealizada(listado: MutableList<Noticia>){
        listado.forEach {
            if(it.tipo=="chivo" and it.esCopadaPart()){
                mailService.sendMail(Mail("mail", mailD,"asunto","cuerpo"))
            }
            if(it.tipo=="reportaje" and it.seDedicaAMusica){
                mailService.sendMail(Mail("mail",mailD,"asunto","cuerpo"))
            }
        }
    }
}

class ANSISender(destino : AgenciaANSI): PublicacionObserver {
    override fun publicacionRealizada(listado: MutableList<Noticia>) {
        listado.forEach {
            var cadena: String = ""
            if(it.tipo == "chivo"){
                cadena.append("01 chivo")
            }
            else if(it.tipo == "articulo comun"){
                cadena.append("02 articulos comunes")
            }
            else { cadena.append("R") }
            cadena.append(it.desarrollo.first(100))
            cadena.append(it.periodista.nombre)
            if(it.importancia=<10 and it.importancia => 8){
                cadena.append("A")
            }else if(it.importancia=< 7 and it.importancia=>5){
                cadena.append("M")
            }else if(it.importancia=<5){
                cadena.append("baja")
            }
            destino.recibirCadena(cadena)
        }
    }
}