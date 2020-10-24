package std

object Functions {

  //lists
  val list: List[Int] = List(1, 2, 3)
  list.map(_ + 1) //List(2, 3, 4)
  list.fold(0) {
    case (acc, elem) => acc + elem
  }
  list.forall(_ % 2 == 0) //false
  list.filter(_ % 2 == 0) //List(2)

  //options
  val something: Option[Int] = Some(1) // Option(1)
  val empty: Option[Int]     = None

  something.map(_ + 1)        // Some(2)
  something.map(_ + 1)        // Some(2)
  something.map(it => it + 1) // Some(2)
  something.map(it => it + 1) // Some(2)
  something.map {
    case 1 => "This is 1"
    case 2 => "This is 2"
  }

  something.getOrElse(0) // 1
  empty.getOrElse(0)     // 0
  empty.orElse(Some(1))  // Some(1)

  empty.map(_ + 1)         // still None
  something.toList         //empty list or List(2)
  something.contains(2)    // map(_ == 2)  true if empty false
  something.exists(_ == 2) // mandatory to return a boolean in the function

  //eithers
  val rightOrLeft: Either[String, Int] = Right(1)          //this can be Int or String but it's Int
  val leftOrRight: Either[String, Int] = Left("left path") //this can be Int or String but it's String

  rightOrLeft.map(_ + 1)                  //Right(2)
  rightOrLeft.toOption                    //Some(2)
  leftOrRight.map(_ + 1)                  //Left("left path")
  leftOrRight.swap.map(_ => "right path") //Right("right path") <- Either[Int, String]
  rightOrLeft.fold(left => s"This is $left", right => s"This is $right")
  //equivalent

  if (rightOrLeft.isRight)
    s"This is ${rightOrLeft.right}"
  else s"This is ${rightOrLeft.left}"

  rightOrLeft match {
    case Right(x) => s"This is $x"
    case Left(y)  => s"This is $y"
  }

  //pattern matching
  something match {
    case Some(1) => "This is 1"
    case Some(2) => "This is 2"
    case Some(x) => s"This is $x" //This is 1
    case somet: Some[Int] =>
      s"This is $somet" //This is Some(1) Unreachable, Some(x) cover this and the code will always goes through there
    case somet @ Some(1) => s"This is $somet" //This is Some(1)
    case None            => """This 
                               |is 
                               |nothing""".stripMargin
  }

  //inheritance pattern matching
  trait Dummy
  case object Winny  extends Dummy
  case object Kiroco extends Dummy

  val dummy: Dummy = Winny

  dummy match {
    case Winny  => "uuuuuuuuuuhhhhhhhh danger danger"
    case Kiroco => "uuuuhhhh"
  }

  rightOrLeft match {
    case Right(x) => s"This is $x"
    case Left(y)  => s"This is $y"
  }

}
