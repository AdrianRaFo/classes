trait WithType[T] {
  def theSame(theThing: T): T
}

trait WithTypeWithAnotherTypeInside[F[_]] {
  def theSame[A](theThingF: F[A]): F[A] = theThingF
}

object ClassWithTypeString extends WithType[String] {
  def theSame(theThing: String): String = theThing
}
object ClassWithTypeInt extends WithType[Int] {
  def theSame(theThing: Int): Int = theThing
}

ClassWithTypeString.theSame("Hello World")
ClassWithTypeInt.theSame(1)

object ClassWithTypeList   extends WithTypeWithAnotherTypeInside[List]
object ClassWithTypeOption extends WithTypeWithAnotherTypeInside[Option]

ClassWithTypeList.theSame(List("Hello"))
ClassWithTypeList.theSame[Int](List(1))

ClassWithTypeOption.theSame(Option("Hello"))
ClassWithTypeOption.theSame(None)
ClassWithTypeOption.theSame[Int](Option(1))
ClassWithTypeOption.theSame[Int](None)
