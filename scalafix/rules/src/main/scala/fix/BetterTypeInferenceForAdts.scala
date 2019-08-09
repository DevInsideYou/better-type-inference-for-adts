package fix

import scalafix.v1._
import scala.meta._

class BetterTypeInferenceForAdts extends SemanticRule("BetterTypeInferenceForAdts") {
  import BetterTypeInferenceForAdts._

  final override val description: String =
    "Mixes `Product with Serializable` into `sealed trait`s and `sealed abstract class`es."

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case tree: Defn.Trait if tree.isSealed =>
        patch(tree)(_.templ)

      case tree: Defn.Class if tree.isSealed && tree.isAbstract =>
        patch(tree)(_.templ)
    }.asPatch
  }

  private[this] def patch[T <: Tree](tree: T)(templ: T => Template): Patch = {
    val template = templ(tree)

    if (template.isEmpty)
      Patch.addRight(tree, s" extends $pws")
    else if (template.extendsOneOf("Product", "Serializable"))
      Patch.empty
    else
      template.inits match {
        case Seq() =>
          Patch.addLeft(template, s"extends $pws ")

        case Seq(extensions @ _*) =>
          Patch.addRight(extensions.last, s" with $pws")
      }
  }

  private[this] val pws: String =
    "Product with Serializable"
}

object BetterTypeInferenceForAdts {
  final implicit class ClassOps(private val self: Defn.Class) extends AnyVal {
    def isSealed: Boolean =
      self.mods.exists(_.is[Mod.Sealed])

    def isAbstract: Boolean =
      self.mods.exists(_.is[Mod.Abstract])
  }

  final implicit class TraitOps(private val self: Defn.Trait) extends AnyVal {
    def isSealed: Boolean =
      self.mods.exists(_.is[Mod.Sealed])

    def isAbstract: Boolean =
      self.mods.exists(_.is[Mod.Abstract])
  }

  final implicit class TemplateOps(private val self: Template) extends AnyVal {
    def isEmpty: Boolean =
      self.syntax.isEmpty

    def extendsOneOf(extensions: String*): Boolean =
      extensions.exists(self.inits.map(_.tpe.syntax).contains)
  }
}
