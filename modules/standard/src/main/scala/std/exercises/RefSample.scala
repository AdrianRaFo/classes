package std.exercises

import cats.implicits._
import cats.effect.{IO, Sync}
import cats.effect.concurrent.Ref

class Counter[F[_]: Sync](countRef: Ref[F, Int]) {
  def increment() = countRef.update(_ + 1)

  def returnCount = countRef.get

}

object Counter {
  def apply[F[_]: Sync]: F[Counter[F]] = Ref.of(0).map(new Counter[F](_))
}

object App {
  def main(args: Array[String]): Unit =
    Counter[IO]
      .flatMap(counter =>
        counter.increment() >> counter.increment() >> counter.returnCount.map(v => println(s"final value $v"))
      )
      .unsafeRunSync()
}
