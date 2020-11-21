package std.exercises.recursivity

import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate
import org.scalatest.matchers.should.Matchers

class RecursiveTest extends AnyFunSuite with Matchers {

  val today = LocalDate.now()

  val initialReleaseDate = "10/12/2020"

  val ciberpunk = new CyberpunkImp(initialReleaseDate)

  test("Intento de testear lo que habeis hecho"){
    //Al incluir la fecha en el mensaje tb testeamos que se lanza en la fecha que esperamos
    val expectedFinalMessage = "28/02/2021 - YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!"
    ciberpunk.passingDays(today) shouldBe expectedFinalMessage
    //Al parecer al devolver el String final esto **SI** se puede testear
  }

}
