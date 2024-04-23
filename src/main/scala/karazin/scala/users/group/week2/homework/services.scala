package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation
import karazin.scala.users.group.week2.homework.adt.*
import karazin.scala.users.group.week2.homework.model.*
import karazin.scala.users.group.week2.homework.model.ApiKey.ApiKey
import karazin.scala.users.group.week2.homework.model.Post.PostId
import karazin.scala.users.group.week2.homework.model.User.UserId

/*
  Dummy services
  
  The services need to be implemented in case of running the code
 */
object services:
  
  def getUserProfile(apiKey: ApiKey): ErrorOr[UserProfile] = ???
  def getPosts(userId: UserId): ErrorOr[List[Post]] = ???
  def getComments(postId: PostId): ErrorOr[List[Comment]] = ??? 
  def getLikes(postId: PostId): ErrorOr[List[Like]] = ???
  def getShares(postId: PostId): ErrorOr[List[Share]] = ???
