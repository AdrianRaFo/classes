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

class WhoIsFurro[T](implicit Fu: Furro[T]){
  def isTaFurro: Boolean = Fu.isFurro
}

new WhoIsFurro[Winny].isTaFurro
new WhoIsFurro[Kiroco].isTaFurro

class WhoIsFurro2[T: Furro]{
  def isTaFurro: Boolean = Furro[T].isFurro
}

new WhoIsFurro2[Winny]().isTaFurro

//demon
implicit def fromStringToInt(str: String): Int = str.toIntOption.getOrElse(0)

def giveMeTheIntToReturnString(num: Int): String = num.toString

giveMeTheIntToReturnString(1)
giveMeTheIntToReturnString("1")
giveMeTheIntToReturnString("pollas")