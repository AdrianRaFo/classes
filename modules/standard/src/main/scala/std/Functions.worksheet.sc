import cats.data._
import cats.implicits._

import scala.util.Try

//functions
def requestAFunction(str: String)(function: Short => Int): Int = function(str.toShort)
val function: Short => Int = { (it: Short) => it.toInt }
def functionAsDef(str: Short): Int = str.toInt
function(1)
functionAsDef(1)
//cleanest one
requestAFunction("1")(function)
requestAFunction("1")(functionAsDef)
requestAFunction("1")(_.toInt)
//explicit param name
requestAFunction("1")((it: Short) => function(it))
requestAFunction("1")((it: Short) => functionAsDef(it))
requestAFunction("1")((it: Short) => it.toInt)
//on block
requestAFunction("1") { it =>
  val result = function(it)
  result
}
requestAFunction("1") { it =>
  val result = functionAsDef(it)
  result
}
requestAFunction("1") { it =>
  val result = it.toInt
  result
}
//(_) is redundant, this is used when the method receive more params but the function doesn't provide all of them.
requestAFunction("1")(function(_))
requestAFunction("1")(functionAsDef(_))
//pattern matching (always a block)
requestAFunction("1") {
  case it if it <= 0 => function(0)
  case it => function(it)
}
requestAFunction("1") {
  case it if it <= 0 => functionAsDef(0)
  case it => function(it)
}
requestAFunction("1") {
  case it if it <= 0 => 0
  case it => it.toInt
}
//pattern matching with explicit type
requestAFunction("1") { case it: Short => function(it) }
requestAFunction("1") { case it: Short => functionAsDef(it) }
requestAFunction("1") { case it: Short => it.toInt }

//nested functions
val chorizo: String => Int => Boolean = { str: String => int: Int => str.toInt == int }
val chorizoSinString: Int => Boolean = chorizo("1")
val rabilloDelChorizo1: Boolean      = chorizoSinString(1)
val rabilloDelChorizo2: Boolean      = chorizo("1")(1)

//lists
val list: List[Int] = List(1, 2, 3)
list.map(_ + 1)            //List(2, 3, 4)
list.map(elem => elem + 1) //List(2, 3, 4)
list.foldLeft(0) {
  case (acc, elem) => acc + elem
} //6
list.forall(_ % 2 == 0) //false
list.filter(_ % 2 == 0) //List(2)

//options
val something: Option[Int] = Some(1) // Option(1)
val empty: Option[Int]     = None
something.map(_ + 1)        // Some(2)
something.map(it => it + 1) // Some(2)
something.map {
  case 1 => "This is 1"
  case 2 => "This is 2"
}
something.getOrElse(0)   // 1
empty.getOrElse(0)       // 0
empty.orElse(Some(1))    // Some(1)
empty.map(_ + 1)         // still None
something.toList         //empty list or List(1)
something.contains(1)    // map(_ == 2)  true, false if empty
something.exists(_ == 1) // mandatory to return a boolean in the function

//eithers
val right: Either[String, Int] = Right(1)          //this can be Int or String but it's Int
val left: Either[String, Int]  = Left("left path") //this can be Int or String but it's String
right.map(_ + 1)                 //Right(2)
right.toOption                   //Some(2)
left.map(_ + 1)                  //Left("left path")
left.swap.map(_ => "right path") //Right("right path") <- Either[Int, String]
right.fold(left => s"This is $left", right => s"This is $right")
//equivalent
//if (right.isRight)
//  s"This is ${right.right.get}"
//else s"This is ${right.left.get}"

right match {
  case Right(x) => s"This is $x"
  case Left(y)  => s"This is $y"
}

//traverse
val listOfValidated = List("1", "a", "2", "b")
listOfValidated.traverse(elem => Try(elem.toInt))
