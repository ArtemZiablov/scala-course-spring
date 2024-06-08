package karazin.scala.users.group.week4.homework

import cats.data.EitherT
import cats.implicits.*
import cats.syntax.all.*
import cats.{Functor, Monad}
import karazin.scala.users.group.week4.homework.errors.{DomainError, FilterError}

import scala.concurrent.{ExecutionContext, Future}

object either:

  extension [F[_], E <: List[DomainError], V](e: EitherT[F, E, List[V]])
    def withFilter(p: List[V] => Boolean)(using Monad[F]): EitherT[F, List[DomainError], List[V]] =
      e.flatMap[List[DomainError], List[V]] { v =>
        if p(v) then EitherT.rightT[F, List[DomainError]](v)
        else EitherT.leftT[F, List[V]](List(FilterError))
      }
