package std.exercises

import scala.io.StdIn

trait ConsoleIO[F[_]] {
  def readBoolean: F[Boolean]
  def readInt: F[Integer]
  def readString: F[String]
}
//Aqui el adri se viene arriba

trait ToF[F[_]] {
  def function[A]: A => F[A]
}
object ToF {
  implicit val toF = new ToF[Option] {
    def function[A]: A => Option[A] = Option(_)
  }
}

class ConsoleIOImpl[F[_]](implicit toF: ToF[F]) extends ConsoleIO[F] {
  def readBoolean: F[Boolean] = toF.function(StdIn.readBoolean)
  def readInt: F[Integer]     = toF.function(StdIn.readInt)
  def readString: F[String]   = toF.function(StdIn.readLine)
}
