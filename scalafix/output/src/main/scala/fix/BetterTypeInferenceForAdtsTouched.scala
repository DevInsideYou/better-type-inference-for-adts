package fix

object BetterTypeInferenceForAdtsTouched {
  object a {
    sealed trait MyTrait extends Product with Serializable
    sealed abstract class MyClass extends Product with Serializable
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Product with Serializable
  }

  object b {
    sealed trait MyTrait extends Product with Serializable {}
    sealed abstract class MyClass extends Product with Serializable {}
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Product with Serializable {}
  }

  object c {
    sealed trait MyTrait extends Product with Serializable { val body = "hello world" }
    sealed abstract class MyClass extends Product with Serializable { val body = "hello world" }
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Product with Serializable { val body = "hello world" }
  }

  trait Animal // not sealed

  object d {
    sealed trait MyTrait extends Animal with Product with Serializable
    sealed abstract class MyClass extends Animal with Product with Serializable
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Animal with Product with Serializable
    sealed abstract class MyClass3[A, B](name: A)(implicit b: B) extends Animal with Product with Serializable {}
    sealed abstract class MyClass4[A, B](name: A)(implicit b: B) extends Animal with Product with Serializable { val body = "hello world" }
  }

  trait Mammal // not sealed

  object e {
    sealed trait MyTrait extends Animal with Mammal with Product with Serializable
    sealed abstract class MyClass extends Animal with Mammal with Product with Serializable
    sealed abstract class MyClass2[A, B](name: A)(implicit b: B) extends Animal with Mammal with Product with Serializable
    sealed abstract class MyClass3[A, B](name: A)(implicit b: B) extends Animal with Mammal with Product with Serializable {}
    sealed abstract class MyClass4[A, B](name: A)(implicit b: B) extends Animal with Mammal with Product with Serializable { val body = "hello world" }
  }
}
