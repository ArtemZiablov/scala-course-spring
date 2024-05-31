//package karazin.scala.users.group.week4.homework
//
//import java.util.UUID
//
//// Do not forget to import custom implementation
//import karazin.scala.users.group.week4.homework.adt.*
//import karazin.scala.users.group.week4.homework.model.*
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
