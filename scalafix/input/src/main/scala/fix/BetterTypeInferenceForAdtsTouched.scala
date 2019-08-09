/*
rule = BetterTypeInferenceForAdts
 */
package fix

object BetterTypeInferenceForAdtsTouched {
  object a {
    sealed trait MyTrait
    sealed abstract class MyClass
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B)
  }

  object b {
    sealed trait MyTrait {}
    sealed abstract class MyClass {}
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) {}
  }

  object c {
    sealed trait MyTrait { val body = "hello world" }
    sealed abstract class MyClass { val body = "hello world" }
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) { val body = "hello world" }
  }

  trait Animal // not sealed

  object d {
    sealed trait MyTrait extends Animal
    sealed abstract class MyClass extends Animal
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Animal
    sealed abstract class MyClass3[A, B](name: A)(implicit b: B) extends Animal {}
    sealed abstract class MyClass4[A, B](name: A)(implicit b: B) extends Animal { val body = "hello world" }
  }

  trait Mammal // not sealed

  object e {
    sealed trait MyTrait extends Animal with Mammal
    sealed abstract class MyClass extends Animal with Mammal
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Animal with Mammal
    sealed abstract class MyClass3[A, B](name: A)(implicit b: B) extends Animal with Mammal {}
    sealed abstract class MyClass4[A, B](name: A)(implicit b: B) extends Animal with Mammal { val body = "hello world" }
  }
}
