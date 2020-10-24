package std

import scala.util.Try

object Types {

  val text: String = "abcds"
  val number: Int = 123
  val decimal: Double = 123.23
  val trueOrFalse: Boolean = true
  val character: Char = 'c'

  val list: List[Int] = List(1, 2, 3)
  val listWithoutDuplicates = Set(1, 1, 2, 3) //Set(1, 2, 3)
  val map: Map[Int, String] = Map(1 -> "a", 2 -> "b")

  val tuples: (Int, String, Boolean) = (1, "a", true)

  val something: Option[Int] = Some(1) // Option(1)
  val empty: Option[Int] = None
  val nullHandling = Option(null) // None

  val rightOrLeft: Either[String, Int] = Right(1) //this can be Int or String but it's Int
  val leftOrRight: Either[String, Int] = Left("left path") //this can be Int or String but it's String

  val success = Try(1) //Success(1)
  val failure = Try(throw new RuntimeException("this is Winny fault")) //Failure(Exception)

}
