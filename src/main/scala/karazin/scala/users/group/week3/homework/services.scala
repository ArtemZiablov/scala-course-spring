<<<<<<< HEAD
//package karazin.scala.users.group.week3.homework
//
//import java.util.UUID
//
//// Do not forget to import custom implementation
//import karazin.scala.users.group.week3.homework.adt.*
//import karazin.scala.users.group.week3.homework.model.*
//
///*
//  Dummy services
//
//  The services need to be implemented in case of running the code
// */
//object services:
//
//  // Implement and use ApiKey instead of String
//  // Replace `ErrorOr[_]` with ErrorOrT[Future, _]
//  def getUserProfile(apiKey: String): ErrorOr[UserProfile] = ???
//  def getPosts(userId: UUID): ErrorOr[List[Post]] = ???
//  def getComments(postId: UUID): ErrorOr[List[Comment]] = ???
//  def getLikes(postId: UUID): ErrorOr[List[Like]] = ???
//  def getShares(postId: UUID): ErrorOr[List[Share]] = ???
=======
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
>>>>>>> 845e426450ed402700bafe37df16d0a4dc8e95ad
