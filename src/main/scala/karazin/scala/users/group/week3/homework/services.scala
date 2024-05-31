import model.*
import adt.*
import model.ApiKey.ApiKey
import model.Post.PostId
import model.User.UserId

import scala.concurrent.Future


/*
  Dummy services

  The services need to be implemented in case of running the code
 */
object services:

  def getUserProfile(apiKey: ApiKey): ErrorOrT[Future, UserProfile] = ???

  def getPosts(userId: UserId): ErrorOrT[Future, List[Post]] = ???

  def getComments(postId: PostId): ErrorOrT[Future, List[Comment]] = ???

  def getLikes(postId: PostId): ErrorOrT[Future, List[Like]] = ???

  def getShares(postId: PostId): ErrorOrT[Future, List[Share]] = ???
