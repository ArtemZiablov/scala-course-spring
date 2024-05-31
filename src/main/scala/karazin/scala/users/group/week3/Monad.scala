package karazin.scala.users.group.week3

trait Monad[F[_]]:
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  def pure[A](x: A): F[A]

object Monad:
  def apply[F[_]](using monad: Monad[F]): Monad[F] = monad
