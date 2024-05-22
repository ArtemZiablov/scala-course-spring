import adt.ErrorOr
import adt.ErrorOr.Error
import adt.ErrorOr.Value
import karazin.scala.users.group.week3.{Functor, Monad}

object adt:

  case class ErrorOrT[F[_], A](value: F[ErrorOr[A]]):
    def flatMap[B](f: A => ErrorOrT[F, B])(using M: Monad[F]): ErrorOrT[F, B] =
      ErrorOrT(
        M.flatMap[ErrorOr[A], ErrorOr[B]](value) {
          case Error(err) => M.pure(ErrorOr.Error(err))
          case Value(v)   =>
            try
              f(v).value
            catch
              case err: Throwable => M.pure(ErrorOr.Error(err))
        }
      )

    def map[B](f: A => B)(using Functor: Functor[F]): ErrorOrT[F, B] =
      ErrorOrT(Functor.map(value)(v => v.map(f)))

    def withFilter(p: A => Boolean)(using Functor: Functor[F]): ErrorOrT[F, A] =
      ErrorOrT(Functor.map(value)(v => v.withFilter(p)))

  enum ErrorOr[+V]:

    /*
      Two case must be defined:
      * a case for a regular value
      * a case for an error (it should contain an actual throwable)
     */

    /*A case for a regular value*/
    case Value(v: V) extends ErrorOr[V]

    /*A case for an error (it should contain an actual throwable)*/
    case Error(e: Throwable) extends ErrorOr[Nothing]


    /*
      The method is used for defining execution pipelines
      Provide a type parameter, an argument and a result type

      Make sure that if an internal function is failed with an exception
      the exception is not thrown but the case for an error is returned
    */
    def flatMap[Q](f: V => ErrorOr[Q]): ErrorOr[Q] =
      this match
        case Error(err) => Error(err)
        case Value(v) =>
          try
            f(v)
          catch
            case ex: Throwable => Error(ex)

    /*
      The method is used for changing the internal object
      Provide a type parameter, an argument and a result type

      Make sure that if an internal function is failed with an exception
      the exception is not thrown but the case for an error is returned
     */
    def map[Q](f: V => Q): ErrorOr[Q] =
      this match
        case Error(err) => Error(err)
        case Value(v) =>
          try
            ErrorOr.Value(f(v))
          catch
            case ex: Throwable => Error(ex)

    def withFilter(p: V => Boolean): ErrorOr[V] =
      this match
        case Error(err) => Error(err)
        case Value(v)   => if p(v) then Value(v) else Error(IllegalArgumentException("Predicate is wrong"))

    def flatten[U](using ev: V <:< ErrorOr[U]): ErrorOr[U] =
      this match
        case Error(err) => Error(err)
        case Value(v)   =>
          try
            ev(v)
          catch
            case ex: Throwable => Error(ex)

    def foreach[U](f: V => U): Unit =
      this match
        case Value(v) => f(v)
        case _        => ()

    def fold[Q](ifEmpty: => Q)(f: V => Q): Q =
      this match
        case Value(v) => f(v)
        case _        => ifEmpty

    def foldLeft[Q](acc: Q)(op: (Q, V) => Q): Q =
      this match
        case Error(err) => acc
        case Value(v)   => op(acc, v)

    def foldRight[Q](acc: Q)(op: (V, Q) => Q): Q =
      this match
        case Error(err) => acc
        case Value(v)   => op(v, acc)


  // Companion object to define constructor
  object ErrorOr:
    /*
      Provide a type parameter, an argument and a result type

      Make sure that if an internal function is failed with an exception
      the exception is not thrown but the case for an error is returned
    */
    def apply[V](expr: => V): ErrorOr[V] =
      try
        Value(expr)
      catch
        case e: Throwable => Error(e)
