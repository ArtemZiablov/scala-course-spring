
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
