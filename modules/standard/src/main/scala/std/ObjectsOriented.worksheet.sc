//no params, we can inherit from multiple traits
trait Interface {
  def interfaceName: String
}

//params, only can inherit from one abstract class
abstract class MyAbstractClass(age: Int)

//unique instance created at the start of the program
object ObjectsOriented {

  def main(args: Array[String]): Unit =
    println("Hello World!")

}

//multiple instances
class MyClass(name: String, age: Int) extends MyAbstractClass(age) with Interface {

  def interfaceName: String = "MyClass"

  val noMutable = true

  var mutable = false
  mutable = true

  def executedWhenCalled() = true //methods, the last thing written is the one returned

  lazy val winy: String = throw new RuntimeException("Winy is lazy even to fail") // Executed the first time is called
  println("ha ha") //This is printed
  println(winy)    //Exception thrown here because it's executed here

}

//used to model, multiple instances, immutable
case class MyCaseClass(name: String, age: Int)
//companion object, same name as the class and placed along the class, used to host related implicits and other static values
object MyCaseClass {
  implicit val decoder: String = "Decoder for MyCaseClass"
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