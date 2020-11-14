package std.exercises.tagless

import scala.io.StdIn
import scala.util.Try

trait ConsoleIO[F[_]] {
  def readBoolean: F[Boolean]
  def readInt: F[Integer]
  def readString: F[String]
}
//Aqui el adri se viene arriba

trait ToF[F[_]] {
  def function[A]: Try[A] => F[A]
}
object ToF {
  implicit val toQuesarito: ToF[Quesarito] = new ToF[Quesarito] {
    def function[A]: Try[A] => Quesarito[A] = _.toOption
  }
  implicit val toKebab: ToF[Kebab] = new ToF[Kebab] {
    def function[A]: Try[A] => Kebab[A] = identity
  }
}

class ConsoleIOImpl[F[_]](implicit toF: ToF[F]) extends ConsoleIO[F] {
  def readBoolean: F[Boolean] = toF.function(Try(StdIn.readBoolean))
  def readInt: F[Integer]     = toF.function(Try(StdIn.readInt))
  def readString: F[String]   = toF.function(Try(StdIn.readLine))
}
