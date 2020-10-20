package std

object Types {

  val text: String = "abcds"
  val number: Int = 123
  val decimal: Double = 123.23

  val list: List[Int] = List(1, 2, 3)
  val listWithoutDuplicates = Set(1, 1, 2, 3) //Set(1, 2, 3)
  val map: Map[Int, String] = Map(1 -> "a", 2 -> "b")

  val tuples: (Int, String, Boolean) = (1, "a", true)

  val optional: Option[Int] = Some(1)
  val empty: Option[Int] = None

  val rightOrLeft:Either[String, Int] = Right(1) //this can be Int or String but it's Int
  val leftOrRight: Either[String, Int] = Left("left path") //this can be Int or String but it's String

  

}
