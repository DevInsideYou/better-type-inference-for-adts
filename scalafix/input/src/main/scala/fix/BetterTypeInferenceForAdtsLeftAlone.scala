/*
rule = BetterTypeInferenceForAdts
 */
package fix

object BetterTypeInferenceForAdtsLeftAlone {
  object a {
    trait MyTrait // not sealed
    sealed class MyClass // not abstract
    class MyClass2 // neither sealed nor abstract
  }

  object b { // already has one of the two mixed in so it's assumed to be on purpose
    sealed trait MyTrait extends Product
    sealed abstract class MyClass extends Product
  }

  object c { // already has one of the two mixed in so it's assumed to be on purpose
    sealed trait MyTrait extends Serializable
    sealed abstract class MyClass extends Serializable
  }
}
