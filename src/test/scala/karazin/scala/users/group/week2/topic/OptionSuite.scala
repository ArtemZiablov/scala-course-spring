package karazin.scala.users.group.week2.topic

import karazin.scala.users.group.model.DummyError
import karazin.scala.users.group.week2.topic.adt.Option
import karazin.scala.users.group.week2.topic.adt.Option.{None, Some}
import munit.ScalaCheckSuite
import org.scalacheck.Prop.*

class OptionSuite extends ScalaCheckSuite {

  property("applying pure value returns Some") {
    forAll { (v: String) =>
      Option(v) == Some(v)
    }
  }

  property("applying null value returns None") {
    Option(null) == None
  }

  property("flatmap returns Some if applied to Some and the function returns Some") {
    forAll { (v: Int, f: Int => String) =>
      Option(v).flatMap(_ => Option(f(v))) == Option(f(v))
    }
  }

  property("flatmap returns None if applied to Some but the function returns None") {
    forAll { (v: Int) =>
      Option(v).flatMap(_ => None) == None
    }
  }

  property("flatmap returns None immediately") {
    None.flatMap(_ => throw DummyError) == None
  }

  property("map returns Some if applied to Some") {
    forAll { (v: Int, f: Int => String) =>
      Option(v).map(v => f(v)) == Option(f(v))
    }
  }

  property("map returns None immediately") {
    None.map(_ => throw DummyError) == None
  }

  property("withFilter returns Some if applied to Some and predicate is true") {
    forAll { (v: String) =>
      Option(v).withFilter(_ => true) == Option(v)
    }
  }

  property("withFilter returns None if applied to Some and predicate is false") {
    forAll { (v: String) =>
      Option(v).withFilter(_ => false) == None
    }
  }

  property("withFilter returns None if applied to None immediately") {
    None.withFilter(_ => throw DummyError) == None
  }


  property("flatten returns Some if applied to a nested Some") {
    forAll { (v: String) =>
      Option(Option(v)).flatten == Option(v)
    }
  }

  property("flatten returns None if applied to a nested None") {
    Option(None).flatten == None
  }

  property("flatten returns None if applied to None") {
    None.flatten == None
  }

  property("flatten compilation fails if applied to a none-Option nested") {
    assertNoDiff(
      compileErrors("Option(42).flatten"),
      """|error:
         |Cannot prove that Int <:< karazin.scala.users.group.week2.topic.adt.Option[U]
         |
         |where:    U is a type variable with constraint 
         |.
         |Option(42).flatten
         |                 ^
         |""".stripMargin
    )
  }

  property("foreach applies side effect if applied to Some") {
    forAll { (v: String) =>
      var sideEffect = false
      Option(v).foreach(_ => sideEffect = true)
      sideEffect
    }
  }

  property("foreach ignores side effect if applied to None") {
    None.foreach(_ => throw DummyError)
  }
  
  property("fold returns the result of the function application if applied to Some") {
    forAll { (v: Int, ifEmpty: Int, f: Int => String) =>
      Option(v).fold(ifEmpty)(f) == f(v)
    }
  }

  property("fold returns default if applied to None") {
    forAll { (ifEmpty: Int) =>
      None.fold(ifEmpty)(_ => throw DummyError) == ifEmpty
    }
  }


  property("foldLeft returns the result of the function application if applied to Some") {
    forAll { (v: Int, acc: Int, f: (Int, Int) => Int) =>
      Option(v).foldLeft(acc)(f) == f(acc, v)
    }
  }

  property("foldLeft returns acc if applied to None") {
    forAll { (acc: Int) =>
      None.foldLeft(acc)((_, _) => throw DummyError) == acc
    }
  }

}
