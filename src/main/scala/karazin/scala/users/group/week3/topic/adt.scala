package karazin.scala.users.group.week3.topic

import cats.data.OptionT
import karazin.scala.users.group.week3.{Functor, Monad}

import scala.concurrent.Future

object adt:

  case class OptionT[F[_], A](value: F[Option[A]]):
    def flatMap[B](f: A => OptionT[F, B])(using M: Monad[F]): OptionT[F, B] =
      OptionT(
        M.flatMap[Option[A], Option[B]](value) {
          case Option.Some(v)  => f(v).value
          case Option.None     => M.pure(Option.None) // Future.successful(None)
        }
      )

    def map[B](f: A => B)(using Functor: Functor[F]): OptionT[F, B] =
      OptionT(Functor.map(value)(v => v.map(f)))

    def withFilter(p: A => Boolean)(using Functor: Functor[F]): OptionT[F, A] =
      OptionT(Functor.map(value)(v => v.withFilter(p)))



  /** Represents optional values. Instances of `Option`
   * are either an instance of `Some` or the object `None`.
   */
  enum Option[+V]:

    /** Case `Some[A]` represents existing values of type
     * `A`.
     */
    case Some(x: V) extends Option[V]

    /** This case object represents non-existent values.
     */
    case None extends Option[Nothing]

    /** Returns the result of applying `f`` to this `Option`'s value if
     * this `Option` is nonempty.
     * Returns `None` if this `Option` is empty.
     * Slightly different from `map` in that $f is expected to
     * return an `Option` (which could be `None`).
     *
     *  @param f the function to apply
     */
    def flatMap[Q](f: V => Option[Q]): Option[Q] =
      this match
        case Option.None     => Option.None
        case Option.Some(v)  => f(v)

    /** Returns a `Some` containing the result of applying `f`` to this `Option`'s
     * value if this `Option` is nonempty.
     * Otherwise returns `None`.
     *
     *  @note This is similar to `flatMap` except here,
     *  $f does not need to wrap its result in an `Option`.
     *
     *  @param f the function to apply
     */
    def map[Q](f: V => Q): Option[Q] =
      this match
        case Option.None     => Option.None
        case Option.Some(v)  => Option.Some(f(v))

    /** Returns `Some(v)` if v satisfies the predicate `p` and `None` otherwise.
     *
     * @param p the predicate used to test values.
     */
    def withFilter(p: V => Boolean): Option[V] =
      this match
        case Option.Some(v) if p(v) => Option.Some(v)
        case _                      => Option.None

    /** Returns the nested `Option` value if it is nonempty. Otherwise,
     * returns `None`.
     *
     *
     * @example {{{
     * Some(Some("something")).flatten == Some("something")
     * }}}
     * @param ev an implicit conversion that asserts that the value is
     *           also an `Option`.
     */
    def flatten[U](using ev: V <:< Option[U]): Option[U] =
      this match
        case Option.Some(v) => ev(v)
        case _              => Option.None

    /** Apply the given procedure `f` to the `Option`'s value,
     * if it is nonempty. Otherwise, do nothing.
     *
     * @param f the procedure to apply.
     */
    def foreach[U](f: V => U): Unit =
      this match
        case Option.None    => ()
        case Option.Some(v) => f(v)

    /**
     * Returns the result of applying f to this Option's value if the Option is nonempty. Otherwise, evaluates expression ifEmpty.
     *
     * @param  ifEmpty the expression to evaluate if empty.
     * @param  f       the function to apply if nonempty.
     */
    def fold[Q](ifEmpty: => Q)(f: V => Q): Q =
      this match
        case Option.None    => ifEmpty
        case Option.Some(v) => f(v)

    def foldLeft[Q](acc: Q)(op: (Q, V) => Q): Q =
      this match
        case Option.None    => acc
        case Option.Some(v) => op(acc, v)

  object Option:
    /** An `Option`` factory which creates `Some(x)`` if the argument is not null,
     * and `None` if it is null.
     *
     * @param x the value
     */
    def apply[V](v: V): Option[V] =
      if v == null then Option.None else Option.Some(v)
