package std.exercises.recursivity

import scala.annotation.tailrec
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

//Crea un programa que reciba la fecha de lanzamiento del Cyberpunk 2077 por consola y
//cogiendo la fecha actual, itere por dia. Dos semanas antes del lanzamiento
//debe publicar un comunicado diciendo que se retrasa 20 dias.
//La funcion para iterar sobre la fecha actual debe ser recursiva y
//una vez retrasada la fecha de lanzamiento debe seguir iterando 
//hasta 4 veces con las correspondientes fechas de lanzamiento sin salirse de memoria.
//Para acabar despues de los 4 retrasos debe publicar un comunicado celebrando el lanzamiento.
//
//b. Modificar el programa anterior para que dos semanas y
//  un dia antes del lanzamiento publicar otro mensaje confirmando esa fecha de salida.
//
//c. Modificar el programa una vez mas para que sea capaz de iterar infinitamente. 
//  Al terminar una iteracion debe preguntarte si deseas cancelar o conservar la reserva del juego.

//Note: Todos los comunicados deben llevar la fecha en la que se estan publicando

//Hint: Puedes usar la anotacion @tailrec para asegurarte de que tu recursividad esta optimizada.

class CyberpunkImp(date: String) {
  import CyberpunkImp._
  val releaseDate: LocalDate = LocalDate.parse(date, pattern)

  @tailrec
  final def passingDays(currentDay: LocalDate, delay: Integer = 0, release: LocalDate = releaseDate): Unit = {
    println("AHORA ES " + currentDay.format(pattern))
    val daysBetween = ChronoUnit.DAYS.between(currentDay, release)
    if (daysBetween == 0 && delay >= 4) {
      println("YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!")
    } else if (daysBetween < 15 && delay < 4) {
      println("VAYA! SE HA RETRASADO OTRA VEZ CYBERPUNK")
      val newReleaseDate = release.plusDays(20)
      println("LA NUEVA FECHA DE LANZAMIENTO ES EL " + newReleaseDate.format(pattern))
      passingDays(currentDay.plusDays(1), delay + 1, newReleaseDate)
    } else {
      passingDays(currentDay.plusDays(1), delay, release)
    }
  }
}

object CyberpunkImp {
  val pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}

object RecurSiveExercise extends App {
  val today = LocalDate.now()

  val cyberpunk = new CyberpunkImp("10/12/2020")

  cyberpunk.passingDays(today)
}