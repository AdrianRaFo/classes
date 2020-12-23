
trait Furro[A] {
  def isFurro: Boolean
}
object Furro {
  def apply[A](implicit fu: Furro[A]) = fu
}

case class Winny()
object Winny {
  implicit val winnyFurro = new Furro[Winny] {
    def isFurro: Boolean = true
  }
}
case class Kiroco()
object Kiroco {
  implicit val kirocoFurro = new Furro[Kiroco] {
    def isFurro: Boolean = false
  }
}

class WhoIsFurro[T](implicit Fu: Furro[T]) {
  def isTaFurro: Boolean = Fu.isFurro
}

new WhoIsFurro[Winny].isTaFurro
new WhoIsFurro[Kiroco].isTaFurro

class WhoIsFurro2[T: Furro] {
  def isTaFurro: Boolean = Furro[T].isFurro
}

new WhoIsFurro2[Winny]().isTaFurro

implicit class StringOps(str: String) {
  def isNumerical: Boolean = str.forall(_.isDigit)
}

"123".isNumerical
"1a".isNumerical
"mhe".isNumerical

//demon
implicit def fromStringToInt(str: String): Int = str.toIntOption.getOrElse(0)

def giveMeTheIntToReturnString(num: Int): String = num.toString

giveMeTheIntToReturnString(1)
giveMeTheIntToReturnString("1")
giveMeTheIntToReturnString("meh")

def giveMeTheImplicitIntToReturnString(implicit num: Int): String = num.toString

implicit val impl1: Int = 1

giveMeTheImplicitIntToReturnString
giveMeTheImplicitIntToReturnString(impl1)
giveMeTheImplicitIntToReturnString(1)

//demon summoning
class Demon[T](val negativity: T)
object Demon {
  implicit def demonSummoning[T](implicit negativity: T) = new Demon(negativity)
}

class DemonRequired[T](implicit val demon: Demon[T]) {
  val demonCreatedBy = demon.negativity
}

implicit val iNegativity: String = "Tony"

new DemonRequired[String]().demonCreatedBy

new DemonRequired[Int]().demonCreatedBy