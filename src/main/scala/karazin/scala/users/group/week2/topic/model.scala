package karazin.scala.users.group.week2.topic

import karazin.scala.users.group.week1.topic.model.Post.PostId
import karazin.scala.users.group.week1.topic.model.User.UserId

import java.util.UUID

object model:

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
  
  case class Post(userId: UserId, postId: PostId)
  case class Comment(userId: UserId, postId: PostId)
  case class Like(userId: UserId, postId: PostId)
  case class Share(userId: UserId, postId: PostId)
  case class PostView(post: Post, comments: List[Comment], likes: List[Like], shares: List[Share])
