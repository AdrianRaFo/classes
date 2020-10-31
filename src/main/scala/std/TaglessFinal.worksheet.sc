import std.exercises.ConsoleIOImpl

//trait formado por un tipo F que recibe otro tipo
//metodos abstractos que devuelven un F de el tipo que necesiten
trait Algebra[FA[_]] {
  def pure(str: String)(f: String => FA[String]): FA[String]
}

//implementacion del Algebra tambien con tipo F
class AlgebraImpl[F[_]] extends Algebra[F] {
  def pure(str: String)(function: String => F[String]): F[String] = function(str)
}

//optional
object Algebra {
  def apply[F[_]] = new AlgebraImpl[F]
}

val optAlgebra = Algebra[Option]

//siempre se pide el trait
class UseAlgebra(alg: Algebra[Option]){
  def toOption(str: String) = alg.pure(str)(_)
}

new UseAlgebra(optAlgebra)

//for testing
class FakeAlgebraImpl extends Algebra[Option] {
  def pure(str: String)(f: String => Option[String]): Option[String] = Option("abc")
}

new UseAlgebra(new FakeAlgebraImpl)