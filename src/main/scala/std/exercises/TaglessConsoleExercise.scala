package std

import std.exercises._
import scala.util.Try

//crear un algebra de tagless final que defina 3 metodos, los tres deben leer por consola y devolver un String, Int y Boolean respectivamente
//en el object TaglessConsoleExercise llamar a la implementacion usando el tipo Option de manera que si alguna excepcion ocurriese devolveria None
//deberemos printear por consola el resultado de llamar una vez a cada metodo
object TaglessConsoleExercise extends App {

  val console = new ConsoleIOImpl[Option]

  println(Try(console.readString).toOption.flatten)
  println(Try(console.readInt).toOption.flatten)
  println(Try(console.readBoolean).toOption.flatten)
}
