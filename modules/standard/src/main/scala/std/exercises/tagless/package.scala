package std.exercises

import scala.util.Try

package object tagless {
  type Quesarito[A] = Option[A]
  type Kebab[A] = Try[A]
}
