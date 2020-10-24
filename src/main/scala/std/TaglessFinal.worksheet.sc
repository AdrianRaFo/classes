trait Algebra[F[_]] {
  def doSomething: F[String]
}
class AlgebraImpl[F[_]] extends Algebra[F] {

  def doSomething: F[String] = ???

}
object Algebra {
  def apply[F[_]] = new AlgebraImpl[F]
}
