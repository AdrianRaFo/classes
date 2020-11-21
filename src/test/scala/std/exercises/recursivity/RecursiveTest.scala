package std.exercises.recursivity

import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate

class RecursiveTest extends AnyFunSuite {

  val today = LocalDate.now()

  val initialReleaseDate = "10/12/2020"

  val ciberpunk = new CyberpunkImp(initialReleaseDate)

  test("Intento de testear lo que habeis hecho"){
    ciberpunk.passingDays(today)
  }

}
