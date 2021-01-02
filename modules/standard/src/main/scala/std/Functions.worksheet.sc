//functions
def requestAFunction(str: String)(function: String => Int): Int = function(str)
val function: String => Int = { (it:String) => it.toInt }
def functionAsDef(str: String) : Int = str.toInt
function("1")
functionAsDef("1")
requestAFunction("1")(function) //clean one
requestAFunction("1")((it: String) => function(it)) //explicit but less clean
requestAFunction("1")(function(_)) //(_) is redundant, this is used when the method receive more params but the function doesn't provide all of them.
requestAFunction("1")((it: String) => functionAsDef(it)) //explicit but less clean
requestAFunction("1")(functionAsDef(_)) //(_) is redundant, this is used when the method receive more params but the function doesn't provide all of them.
requestAFunction("1")(functionAsDef) //clean one
requestAFunction("1")((it: String) => it.toInt) //explicit but less clean
requestAFunction("1")(_.toInt) //clean one

//nested functions
val chorizo: String => Int => Boolean = { str: String => int: Int => str.toInt == int }
val chorizoSinString: Int => Boolean = chorizo("1")
val rabilloDelChorizo1: Boolean = chorizoSinString(1)
val rabilloDelChorizo2: Boolean = chorizo("1")(1)

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
val empty: Option[Int] = None
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
val left: Either[String, Int] = Left("left path")  //this can be Int or String but it's String
right.map(_ + 1)                  //Right(2)
right.toOption                    //Some(2)
left.map(_ + 1)                   //Left("left path")
left.swap.map(_ => "right path")  //Right("right path") <- Either[Int, String]
right.fold(left => s"This is $left", right => s"This is $right")
//equivalent
//if (right.isRight)
//  s"This is ${right.right.get}"
//else s"This is ${right.left.get}"

right match {
    case Right(x) => s"This is $x"
    case Left(y)  => s"This is $y"
  }
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

//inheritance pattern matching
trait Dummy

case object Winny extends Dummy
case object Kiroco extends Dummy
val dummy: Dummy = Winny
dummy match {
    case Winny  => "uuuuuuuuuuhhhhhhhh danger danger"
    case Kiroco => "uuuuhhhh"
  }
