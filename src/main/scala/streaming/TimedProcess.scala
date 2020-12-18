package streaming

import cats.effect.{ExitCode, IO, IOApp}

import scala.concurrent.duration._
import fs2.Stream

object TimedProcess extends IOApp {

  def doSomething(num: Long) = IO(println(s"something $num"))

  def usingStreams[A](doIO: Long => IO[A]) =
    Stream.awakeEvery[IO](2.seconds).evalMap(dur => doIO(dur.toSeconds)).repeat

  override def run(args: List[String]): IO[ExitCode] =
    usingStreams(doSomething).compile.drain.as(ExitCode.Success)

}
