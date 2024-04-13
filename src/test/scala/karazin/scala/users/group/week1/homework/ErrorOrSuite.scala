package karazin.scala.users.group.week1.homework

import munit.ScalaCheckSuite
import org.scalacheck.Prop.*
import adt.ErrorOr
import adt.ErrorOr.Value
import adt.ErrorOr.Error
import org.scalacheck.Gen
import karazin.scala.users.group.model.DummyError
import java.lang

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
      ErrorOr(value).flatMap(_ => throw throwable)  == Error(throwable)
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

}
