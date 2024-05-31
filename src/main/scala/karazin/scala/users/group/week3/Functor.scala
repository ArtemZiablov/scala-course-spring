package karazin.scala.users.group.week3

import cats.instances.future.*


trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

object Functor:
  def apply[F[_]](using functor: Functor[F]): Functor[F] = functor