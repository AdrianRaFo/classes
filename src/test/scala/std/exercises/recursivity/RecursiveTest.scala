package std.exercises.recursivity

import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate

import org.scalatest.matchers.should.Matchers
import std.exercises.tagless.ConsoleIO

import scala.util.Try

class RecursiveTest extends AnyFunSuite with Matchers {

  val today: LocalDate = LocalDate.now()

  test("Intento de testear lo que habeis hecho") {

    val fakeConsoleIO: ConsoleIO[Try] = new ConsoleIO[Try] {
      def readBoolean: Try[Boolean] = throw new RuntimeException("This should not be used")
      def readInt: Try[Integer] = throw new RuntimeException("This should not be used")
      def readString: Try[String] = Try("10/12/2020")
    }

    val ciberpunk = new CyberpunkImp(fakeConsoleIO)

    //Al incluir la fecha en el mensaje tb testeamos que se lanza en la fecha que esperamos
    //La fecha deberia ser inicial.plusDays(80)
    val expectedFinalMessage = "28/02/2021 - YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!"
    ciberpunk.passingDays(today) shouldBe expectedFinalMessage
    //Al parecer al devolver el String final esto **SI** se puede testear
  }

}
