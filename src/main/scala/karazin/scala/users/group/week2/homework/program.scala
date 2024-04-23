package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation

import karazin.scala.users.group.week2.homework.adt.*
import karazin.scala.users.group.week2.homework.model.*
import karazin.scala.users.group.week2.homework.services.*

object program:

  /*
   Getting view for all user's posts
   Provide a result type
  
   Do not change the result type, it must be `ErrorOr[List[PostView]]`
  */
  def getPostsViews(apiKey: String)(commentsFilter: List[Comment] => Boolean,
                                    likesFilter: List[Like] => Boolean): ErrorOr[List[PostView]] =
    for
      profile <- getUserProfile(apiKey)
      posts <- getPosts(profile.userId)
      postsView <- posts.foldLeft(ErrorOr(List.empty[PostView])) {
        (acc, elem) => acc flatMap
          (list => getPostView(elem)(commentsFilter, likesFilter) map (list :+ _))
      }
    yield postsView

  // Desugared
  def getPostsViewDesugared(apiKey: String)(commentsFilter: List[Comment] => Boolean,
                                            likesFilter: List[Like] => Boolean): ErrorOr[List[PostView]] =
    ???

  /*
    Getting view for a particular user's post
    Provide an argument and a result type
  */
  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
                              likesFilter: List[Like] => Boolean): ErrorOr[PostView] =
    for
      comments <- getComments(post.postId)
      if commentsFilter(comments)
      likes <- getLikes(post.postId)
      if likesFilter(likes)
      shares <- getShares(post.postId)
    yield PostView(post, comments, likes, shares)

  // Desugared
  def getPostViewDesugared(post: Post)(commentsFilter: List[Comment] => Boolean,
                                       likesFilter: List[Like] => Boolean): ErrorOr[PostView] =
    getComments(post.postId).withFilter(commentsFilter) flatMap { comments =>
      getLikes(post.postId).withFilter(likesFilter) flatMap { likes =>
        getShares(post.postId) map { shares =>
          PostView(post, comments, likes, shares)
        }
      }
    }





  