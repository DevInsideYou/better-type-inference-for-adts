# Scalafix rules for Better Type Inference For ADTs

Mixes `Product with Serializable` into `sealed trait`s and `sealed abstract class`es. Does not do anything if either of `Product` or `Serializable` is already mixed in since it assumes that this has been done on purpose.

**Created solely for education purposes. Use at your own risk!**

## Installation & Usage
Either clone the repo and then in the `scalafix` directory run `sbt publishLocal` and then use it in your build like so
```scala
ThisBuild / scalafixDependencies += "com.devinsideyou" %% "scalafix" % "0.0.1-SNAPSHOT"
```
and then run it with `scalafix BetterTypeInferenceForAdts` from sbt or run directly from github (slower) with
```bash
scalafix --rules=github:DevInsideYou/better-type-inference-for-adts
```
[![Watch on YouTube](resources/thumbnail_youtube.jpg)](https://youtu.be/uh7VFcOpu20 "Watch on YouTube")
