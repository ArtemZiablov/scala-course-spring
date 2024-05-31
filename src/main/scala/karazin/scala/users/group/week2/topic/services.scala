package karazin.scala.users.group.week2.topic

import karazin.scala.users.group.week2.topic.model.Post.PostId
import karazin.scala.users.group.week2.topic.model.User.UserId

import java.util.UUID

// Do not forget to import custom implementation
import karazin.scala.users.group.week2.topic.adt.*
import karazin.scala.users.group.week2.topic.model.*

/*
  Dummy services
  
  The services need to be implemented in case of running the code
 */
object services:
  
  def getUserProfile(apiKey: String): Option[UserProfile] = ???
  def getPosts(userId: UserId): Option[List[Post]] = ???
  def getComments(postId: PostId): Option[List[Comment]] = ???
  def getLikes(postId: PostId): Option[List[Like]] = ???
  def getShares(postId: PostId): Option[List[Share]] = ???
