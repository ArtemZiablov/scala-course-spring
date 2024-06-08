<<<<<<< HEAD
//package karazin.scala.users.group.week3.homework
//
//import java.util.UUID
//
///*
//  Consider the way to implement blog structure (getting rid of details):
//  * each user has unique id
//  * each post belongs to one user and has unique id
//  * each comment belongs to one user (author of the comment) and commented post
//  * each share belongs to one user (who shares the post) and shared post
//
//  View represents gathered information due to each service could be responsible only
//  for one domain 
// */
//object model:
//
//  // Implement and use UserId instead of UUID
//  case class UserProfile(userId: UUID)
//
//  // Implement and use PostId instead of UUID
//  case class Post(userId: UUID, postId: UUID)
//  case class Comment(userId: UUID, postId: UUID)
//  case class Like(userId: UUID, postId: UUID)
//  case class Share(userId: UUID, postId: UUID)
//  case class PostView(post: Post, comments: List[Comment], likes: List[Like], shares: List[Share])
=======
import model.Post.PostId
import model.User.UserId

import java.util.UUID

object model:

  object User:
    opaque type UserId <: UUID = UUID

    object UserId:
      def apply(userId: UserId): UserId = userId

      def generate: UserId = UserId(UUID.randomUUID())

  case class UserProfile(userId: UserId)

  object Post:
    opaque type PostId <: UUID = UUID

    object PostId:
      def apply(postId: PostId): PostId = postId

      def generate: PostId = PostId(UUID.randomUUID())

  object ApiKey:
    opaque type ApiKey <: String = String

    object ApiKey:
      def apply(apiKey: ApiKey): ApiKey = apiKey

  case class Post(userId: UserId, postId: PostId)
  case class Comment(userId: UserId, postId: PostId)
  case class Like(userId: UserId, postId: PostId)
  case class Share(userId: UserId, postId: PostId)
  case class PostView(post: Post, comments: List[Comment], likes: List[Like], shares: List[Share])
>>>>>>> 845e426450ed402700bafe37df16d0a4dc8e95ad
