package std.exercises.recursivity

import java.time.LocalDate

import org.scalacheck.Gen
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import std.exercises.tagless.ConsoleIO

import scala.util.Try

class RecursiveTest extends AnyFunSuite with Matchers with ScalaCheckDrivenPropertyChecks {

  val today: LocalDate = LocalDate.now()

  val localDateGen: Gen[LocalDate] =
    Gen.choose(today.toEpochDay, today.withDayOfYear(365).toEpochDay).map(LocalDate.ofEpochDay)

  test("Intento de testear lo que habeis hecho") {

    val fakeConsoleIO: ConsoleIO[Try] = new ConsoleIO[Try] {
      def readBoolean: Try[Boolean] = throw new RuntimeException("This should not be used")
      def readInt: Try[Integer]     = throw new RuntimeException("This should not be used")
      def readString: Try[String]   = Try("10/12/2020")
    }

    val ciberpunk = new CyberpunkImp(fakeConsoleIO)

    //Al incluir la fecha en el mensaje tb testeamos que se lanza en la fecha que esperamos
    val expectedFinalMessage = "28/02/2021 - YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!"
    ciberpunk.passingDays(today) shouldBe expectedFinalMessage
    //Al parecer al devolver el String final esto **SI** se puede testear
  }

  test("Test con generadores") {
    forAll(localDateGen) { localDate =>
      val fakeConsoleIO: ConsoleIO[Try] = new ConsoleIO[Try] {
        def readBoolean: Try[Boolean] = throw new RuntimeException("This should not be used")
        def readInt: Try[Integer] = throw new RuntimeException("This should not be used")
        def readString: Try[String] = Try(CyberpunkImp.pattern.format(localDate))
      }

      val ciberpunk = new CyberpunkImp(fakeConsoleIO)
      val expectedFinalMessage =
        s"${CyberpunkImp.pattern.format(localDate.plusDays(80))} - YA HA SALIDO CYBERPUNK, POR FIN. SE ME SALTAN LAS LAGRIMAS!!!"

      ciberpunk.passingDays(today) shouldBe expectedFinalMessage
    }
  }

}
