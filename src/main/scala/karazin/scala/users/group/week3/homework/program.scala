<<<<<<< HEAD
//package karazin.scala.users.group.week3.homework
//
//// Do not forget to import custom implementation
//import karazin.scala.users.group.week3.homework.adt.*
//import karazin.scala.users.group.week3.homework.model.*
//import karazin.scala.users.group.week3.homework.services.*
//
//object program:
//
//  /*
//    Replace `ErrorOr[List[PostView]] result type with `ErrorOrT[Future, List[PostView]]
//    Provide the implementation for the service
//   */
//  def getPostsViews(apiKey: String)(commentsFilter: List[Comment] => Boolean,
//                                    likesFilter: List[Like] => Boolean): ErrorOr[List[PostView]] = ???
//
//  /* 
//    Replace `ErrorOr[PostView] result type with `ErrorOrT[Future, PostView]
//    Provide the implementation for the service
//  */
//  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
//                              likesFilter: List[Like] => Boolean): ErrorOr[PostView] = ???
//
//
//
//
//
=======
import model.ApiKey.ApiKey
import model.*
import adt.*
import services.*
import karazin.scala.users.group.week3.future.given

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.global

object program:

  def getPostsViews(apiKey: ApiKey)(commentsFilter: List[Comment] => Boolean,
                                    likesFilter: List[Like] => Boolean)
                   (using ExecutionContext): ErrorOrT[Future, List[PostView]] =
    for
      profile <- getUserProfile(apiKey)
      posts <- getPosts(profile.userId)
      postsView <- posts.foldLeft(ErrorOrT(Future.successful(ErrorOr(List.empty[PostView])))) {
        (acc, elem) =>
          acc flatMap
            (list => getPostView(elem)(commentsFilter, likesFilter) map (list :+ _))
      }
    yield postsView


/*
  Getting view for a particular user's post
  Provide an argument and a result type
*/

  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
                              likesFilter: List[Like] => Boolean)
                 (using ExecutionContext): ErrorOrT[Future, PostView] =
    for
      comments <- getComments(post.postId)
      if commentsFilter(comments)
      likes <- getLikes(post.postId)
      if likesFilter(likes)
      shares <- getShares(post.postId)
    yield PostView(post, comments, likes, shares)

>>>>>>> 845e426450ed402700bafe37df16d0a4dc8e95ad
