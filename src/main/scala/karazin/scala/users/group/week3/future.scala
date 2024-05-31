package karazin.scala.users.group.week3

import scala.concurrent.{ExecutionContext, Future}
import karazin.scala.users.group.week3.{Functor, Monad}

object future:

  given (using ExecutionContext): Monad[Future] with Functor[Future] with
    def map[A, B](fa: Future[A])(f: A => B) =
      fa.map(f)

    def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] =
      fa.flatMap(f)
      
    def pure[A](x: A): Future[A] =
      Future.successful(x)
      
  
