package karazin.scala.users.group.week2.homework

import karazin.scala.users.group.model.DummyError
import munit.ScalaCheckSuite
import org.scalacheck.Prop.*
import adt.ErrorOr
import adt.ErrorOr.Value
import adt.ErrorOr.Error

class ErrorOrSuite extends ScalaCheckSuite {

  // Fix Value according to your naming
  property("applying pure value returns Value") {
    forAll { (v: String) =>
      ErrorOr(v) == Value(v)
    }
  }

  property("applying value which throws an exception returns Error") {
    forAll { (throwable: Throwable) =>
      ErrorOr.apply(throw throwable) == Error(throwable)
    }
  }

  // Check the property when ErrorOr represents some non-error case and function returns non-error case
  property("flatmap returns Value if `f` returns Value") {
    forAll { (v: Int, f: Int => String) =>
      ErrorOr(v).flatMap(v => ErrorOr(f(v))) == ErrorOr(f(v))
    }
  }

  // Check the property when ErrorOr represents some non-error case but function returns error case
  property("flatmap returns Error if `f` returns Error") {
    forAll { (value: Int, throwable: Throwable) =>
      ErrorOr(value).flatMap(_ => throw throwable) == Error(throwable)
    }
  }

  // Check property when ErrorOr represents some non-error case
  // and function returns non-error case but execution of function fails with some exception
  property("flatmap returns Error if `f` fails") {
    forAll { (v: Int) =>
      Value(v).flatMap(v => throw DummyError) == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("flatmap returns Error immediately") {
    propBoolean {
      Error(DummyError).flatMap(_ => throw IllegalArgumentException()) == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some non-error case and function returns non-error case
  property("map returns Value if `f` returns Value") {
    forAll { (v: Int, f: Int => String) =>
      Value(v).map(v => f(v)) == Value(f(v))
    }
  }

  // Check property when ErrorOr represents some non-error case
  // and function returns non-error case but execution of function fails with some exception
  property("map returns Error if `f` fails") {
    forAll { (v: Int) =>
      Value(v).map(v => throw DummyError) == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("map returns Error immediately") {
    propBoolean {
      Error(DummyError).map(_ => throw IllegalArgumentException()) == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some value case
  property("withFilter returns Value if applied to Value and predicate is true") {
    forAll { (v: Int) =>
      Value(v).withFilter(_ => true) == Value(v)
    }
  }

  // Check the property when ErrorOr represents some value case
  property("withFilter returns Error if applied to Value and predicate is false") {
    forAll { (v: Int) =>
      Value(v).withFilter(_ => false) match {
        case Error(exception) if exception.isInstanceOf[IllegalArgumentException] => assert(true)
        case _ => fail("Expected Error with IllegalArgumentException")
      }
    }
  }

  // Check the property when ErrorOr represents some error case
  property("withFilter returns Error if applied to Error and predicate is true") {
    forAll { (v: Int) =>
      Error(DummyError).withFilter(_ => true) == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("withFilter returns Error if applied to Error and predicate is false") {
    forAll { (v: Int) =>
      Error(DummyError).withFilter(_ => false) == Error(DummyError)
    }
  }

  // Check the property when a nested ErrorOr represents some value case
  property("flatten returns Value if applied to a nested Value") {
    forAll { (v: Int) =>
      ErrorOr(ErrorOr(v)).flatten == ErrorOr(v)
    }
  }

  // Check the property when a nested ErrorOr represents some error case
  property("flatten returns Error if applied to a nested Error") {
    forAll { (v: Int) =>
      ErrorOr(Error(DummyError)).flatten == Error(DummyError)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("flatten returns Error if applied to Error") {
    forAll { (v: Int) =>
      Error(DummyError).flatten == Error(DummyError)
    }
  }

  // Check the property when a nested value is not an ErrorOr
  property("flatten compilation fails if applied to a none-??? nested") {
    assertNoDiff(
      compileErrors("ErrorOr(42).flatten"),
      """|error:
         |Cannot prove that Int <:< karazin.scala.users.group.week2.homework.adt.ErrorOr[U]
         |
         |where:    U is a type variable with constraint 
         |.
         |ErrorOr(42).flatten
         |                  ^""".stripMargin
    )
  }

  // Check the property when ErrorOr represents some value case
  property("foreach applies side effect if applied to Value") {
    forAll { (v: Int) =>
      var sideEffect = false
      Value(v).foreach(_ => sideEffect = true)
      sideEffect
    }
  }

  // Check the property when ErrorOr represents some error case
  property("foreach ignores side effect if applied to Error") {
    propBoolean {
      var sideEffect = true
      Error(DummyError).foreach(_ => sideEffect = false)
      sideEffect
    }
  }

  // Check the property when ErrorOr represents some value case
  property("fold returns the result of the function application if applied to Value") {
    forAll { (v: Int, default: Int, f: Int => String) =>
      Value(v).fold(default)(f) == f(v)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("fold returns default if applied to Error") {
    forAll { (default: Int, f: Int => String) =>
      Error(DummyError).fold(default)(f) == default
    }
  }

  // Check the property when ErrorOr represents some value case
  property("foldLeft returns the result of the function application if applied to Value") {
    forAll { (v: Int, acc: Int, f: (Int, Int) => Int) =>
      Value(v).foldLeft(acc)(f) == f(acc, v)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("foldLeft returns acc if applied to Error") {
    forAll { (acc: Int, f: (Int, Int) => Int) =>
      Error(DummyError).foldLeft(acc)(f) == acc
    }
  }

  // Check the property when ErrorOr represents some value case
  property("foldRight returns the result of the function application if applied to Value") {
    forAll { (v: Int, acc: Int, f: (Int, Int) => Int) =>
      Value(v).foldRight(acc)(f) == f(v, acc)
    }
  }

  // Check the property when ErrorOr represents some error case
  property("foldRight returns acc if applied to Error") {
    forAll { (acc: Int, f: (Int, Int) => Int) =>
      Error(DummyError).foldRight(acc)(f) == acc
    }
  }

}
