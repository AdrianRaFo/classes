import cats.data.{NonEmptyList, ValidatedNel}
import cats.data.Validated.{Invalid, Valid}

import scala.util.Try

val text: String         = "abcds"
val number: Int          = 123
val decimal: Double      = 123.23
val trueOrFalse: Boolean = true
val character: Char      = 'c'

val list: List[Int]       = List(1, 2, 3)
val listWithoutDuplicates = Set(1, 1, 2, 3) //Set(1, 2, 3)
val map: Map[Int, String] = Map(1 -> "a", (2 ,"b"))
map + (1 -> "c") //Map(1 -> c, 2 -> b)

val tuples: (Int, String, Boolean) = (1, "a", true)

val something: Option[Int] = Some(1) // Option(1)
val empty: Option[Int]     = None
val nullHandling           = Option(null) // None

val rightOrLeft: Either[String, Int] = Right(1)          //this can be Int or String but it's Int
val leftOrRight: Either[String, Int] = Left("left path") //this can be Int or String but it's String

val success = Try(1)                                                 //Success(1)
val failure = Try(throw new RuntimeException("this is Winny fault")) //Failure(Exception)

//From cats: ValidatedNel, validation with error accumulation
val validate: ValidatedNel[String, Int] = Valid(1)
val invalid: ValidatedNel[String, Int] = Invalid(NonEmptyList.one("error"))

//pattern matching
something match {
  case Some(1) => "This is 1"
  case Some(2) => "This is 2"
  case Some(x) => s"This is $x" //This is 1
  //case somet: Some[Int] => s"This is $somet" //This is Some(1) Unreachable, Some(x) cover this and the code will always goes through there
  //case somet @ Some(1) => s"This is $somet" //This is Some(1), Some(1) cover this and the code will always goes through there
  //case somet @ Some(x) => s"This is $somet with $x inside" //This is Some(1) with 1 inside, Some(x) cover this and the code will always goes through there
  case None => """This
                 |is
                 |nothing""".stripMargin
}