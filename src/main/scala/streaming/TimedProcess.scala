package streaming

import cats.effect.{ExitCode, IO, IOApp}

import scala.concurrent.duration._
import fs2.Stream

object TimedProcess extends IOApp {

  def process(seconds: Long): IO[Unit] = IO(println(s"Emitted after $seconds seconds from the beginning"))

  def infiniteStreaming[A](doIO: Long => IO[A]): Stream[IO, A] =
    Stream.awakeEvery[IO](2.seconds).evalMap(dur => doIO(dur.toSeconds)).repeat

  override def run(args: List[String]): IO[ExitCode] =
    infiniteStreaming(process).compile.drain.as(ExitCode.Success)

}
