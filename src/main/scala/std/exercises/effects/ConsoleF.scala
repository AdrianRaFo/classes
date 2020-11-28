package std.exercises.effects

import scala.io.StdIn
import scala.util.Try

trait ConsoleIO[F[_]] {
  def readBoolean: F[Boolean]
  def readInt: F[Integer]
  def readString: F[String]
}

class ConsoleIOImpl[F[_]](implicit toF: ToF[F]) extends ConsoleIO[F] {
  def readBoolean: F[Boolean] = toF.function(Try(StdIn.readBoolean))
  def readInt: F[Integer]     = toF.function(Try(StdIn.readInt))
  def readString: F[String]   = toF.function(Try(StdIn.readLine))
}
