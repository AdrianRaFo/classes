package std.exercises.effects

import scala.annotation.tailrec
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import cats.implicits._
import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.Sync

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

class CyberpunkReschedule[F[_]](consoleReadIO: Console[F])(implicit F: Sync[F]) {
  import CyberpunkReschedule._

  private def requestDateInput: F[LocalDate] = {
    println("Introduzca una fecha inicial de lanzamiento del Cyberpunk 2077")
    consoleReadIO.readString.map(LocalDate.parse(_, pattern)).attempt.flatMap {
      case Right(value) => F.pure(value)
      case Left(_) =>
        println("Fecha no valida.")
        requestDateInput
    }
  }

  @tailrec
  private def passingDays(currentDay: LocalDate, release: LocalDate, delay: Integer = 0): String = {
    val daysBetween: Long = ChronoUnit.DAYS.between(currentDay, release)
    if (daysBetween == 0 && delay >= 4)
      s"${currentDay.format(pattern)} - YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!"
    else if (daysBetween < 15 && delay < 4) {
      println(s"${currentDay.format(pattern)} - VAYA! SE HA RETRASADO OTRA VEZ CYBERPUNK")
      val newReleaseDate: LocalDate = release.plusDays(20)
      println(
        s"${currentDay.format(pattern)} - LA NUEVA FECHA DE LANZAMIENTO ES EL " + newReleaseDate.format(pattern)
      )
      passingDays(currentDay.plusDays(1), newReleaseDate, delay + 1)
    } else
      passingDays(currentDay.plusDays(1), release)
  }

  def calculateReleaseDate(today: LocalDate): F[ExitCode] =
    requestDateInput.map(passingDays(today, _)).attempt.map {
      case Right(exitMsg) =>
        println(exitMsg)
        ExitCode.Success
      case Left(exception) =>
        println(s"Execution failed with error $exception")
        ExitCode.Error
    }

}

object CyberpunkReschedule {
  val pattern: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}

object EffectsExercise extends IOApp {
  val today = LocalDate.now()

  val inputConsoleIO: Console[IO] = new ConsoleImpl[IO]

  val cyberpunk = new CyberpunkReschedule(inputConsoleIO)

  def run(args: List[String]): IO[ExitCode] = cyberpunk.calculateReleaseDate(today)
}
