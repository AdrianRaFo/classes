trait WithType[T] {
  def theSame(theThing: T): T
}

trait WithTypeWithAnotherTypeInside[F[_]] {
  def theSame[A](theThingF: F[A]): F[A] = theThingF
}

object ClassWithTypeString extends WithType[String] {
  def theSame(theThing: String): String = theThing
}
object ClassWithTypeInt extends WithType[Int] {
  def theSame(theThing: Int): Int = theThing
}

ClassWithTypeString.theSame("Hello World")
ClassWithTypeInt.theSame(1)

object ClassWithTypeList   extends WithTypeWithAnotherTypeInside[List]
object ClassWithTypeOption extends WithTypeWithAnotherTypeInside[Option]

ClassWithTypeList.theSame(List("Hello"))
ClassWithTypeList.theSame[Int](List(1))

ClassWithTypeOption.theSame(Option("Hello"))
ClassWithTypeOption.theSame(None)
ClassWithTypeOption.theSame[Int](Option(1))
ClassWithTypeOption.theSame[Int](None)

trait Dummy {
  def whatsAnObject: String
}
trait Fat
case class Winny() extends Dummy {
  val whatsAnObject                           = "I don't know"
  val furroCreyenteSupuestamenteNoPracticante = true
}
case class Kiroco() extends Dummy with Fat {
  val virgen: Boolean = true
  val whatsAnObject   = "An instance of a class"
}

trait WithTypeForClass[T] {
  def theSame: T
}

class ClassWithDummy(theThing: Dummy) extends WithTypeForClass[Dummy] {
  def theSame: Dummy = theThing
  def whatsAnObject  = theThing.whatsAnObject
}

new ClassWithDummy(Winny()).theSame

class ClassWithTypeInheritedDummy[D <: Dummy](theThing: D) extends WithTypeForClass[D] {
  def theSame: D    = theThing
  def whatsAnObject = theThing.whatsAnObject
}

new ClassWithTypeInheritedDummy[Winny](Winny()).theSame.furroCreyenteSupuestamenteNoPracticante

new ClassWithTypeInheritedDummy(Winny()).theSame.whatsAnObject

class ClassWithWinny[D >: Winny](theThing: D) extends WithTypeForClass[D] {
  def theSame: D = theThing
}

new ClassWithWinny[Dummy](Kiroco()).theSame
