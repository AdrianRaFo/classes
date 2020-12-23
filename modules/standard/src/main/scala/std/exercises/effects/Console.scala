package std.exercises.effects

import scala.io.StdIn
import cats.effect.Sync

trait Console[F[_]] {
  def readBoolean: F[Boolean]
  def readInt: F[Integer]
  def readString: F[String]
}

class ConsoleImpl[F[_]](implicit F: Sync[F]) extends Console[F] {
  def readBoolean: F[Boolean] = F.delay(StdIn.readBoolean)
  def readInt: F[Integer]     = F.delay(StdIn.readInt)
  def readString: F[String]   = F.delay(StdIn.readLine)
}