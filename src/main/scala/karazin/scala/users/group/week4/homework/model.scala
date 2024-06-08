package karazin.scala.users.group.week4.homework

import cats.data.EitherT
import karazin.scala.users.group.week4.homework.errors.DomainError
import karazin.scala.users.group.week4.homework.model.Post.PostId
import karazin.scala.users.group.week4.homework.model.User.UserId

import java.time.LocalDate
import java.util.UUID
import scala.concurrent.Future

object model:
  
  type Result[V] = EitherT[Future, List[DomainError], V]

  object User:
    opaque type UserId <: UUID = UUID
    object UserId:
      def apply(userId: UUID): UserId = userId
      def generate: UserId = UserId(UUID.randomUUID())

  case class UserProfile(userId: UserId)

  object Post:
    opaque type PostId <: UUID = UUID

    object PostId:
      def apply(postId: UUID): PostId = postId
      def generate: PostId = PostId(UUID.randomUUID())  

  
  case class RegistrationData(username: String, password: String, email: String,
                              phone: String, birthDate: LocalDate, postalCode: String)
  case class Post(userId: UserId, postId: PostId)
  case class Comment(userId: UserId, postId: PostId)
  case class Like(userId: UserId, postId: PostId)
  case class Share(userId: UserId, postId: PostId)
  case class PostView(post: Post, comments: List[Comment], likes: List[Like], shares: List[Share])
