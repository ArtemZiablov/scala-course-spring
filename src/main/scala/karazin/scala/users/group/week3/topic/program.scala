package karazin.scala.users.group.week3.topic

// Do not forget to import custom implementation
import karazin.scala.users.group.week3.Functor
import karazin.scala.users.group.week3.future.given
import karazin.scala.users.group.week3.topic.adt.*
import karazin.scala.users.group.week3.topic.model.*
import karazin.scala.users.group.week3.topic.model.Post.PostId
import karazin.scala.users.group.week3.topic.model.User.UserId
import karazin.scala.users.group.week3.topic.services.*

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.concurrent.ExecutionContext.global
import scala.util.{Failure, Success}

object program extends App:


  val singleThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor())

  val fixedTreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(10))

  given ExecutionContext = ExecutionContext.global


  // Getting view for a particular user's post
  // The wrong way
//  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
//                              likesFilter: List[Like] => Boolean)
//                             (using ExecutionContext): OptionT[Future, PostView] =
//    for
//      comments <- getComments(post.postId)
//      if commentsFilter(comments)
//      likes <- getLikes(post.postId)
//      if likesFilter(likes)
//      shares <- getShares(post.postId)
//    yield PostView(post, comments, likes, shares)


  // The right way
  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
                              likesFilter: List[Like] => Boolean)
                 (using ExecutionContext): OptionT[Future, PostView] =
    println(s"Main thread: ${Thread.currentThread().getName}")

    val getCommentsService = getComments(post.postId)(using fixedTreadPoolContext)
    val getLikesService = getLikes(post.postId)(using fixedTreadPoolContext)
    val getSharesService = getShares(post.postId)(using fixedTreadPoolContext)

    for
      comments <- getCommentsService
      if commentsFilter(comments)
      likes <- getLikesService
      if likesFilter(likes)
      shares <- getSharesService
    yield PostView(post, comments, likes, shares)


  getPostView(Post(UserId.generate, PostId.generate))(_ => true, _ => true).value.onComplete {
    case Success(value)     => println(s"Completed with $value")
    case Failure(exception) => println(s"Failed with $exception")
  }

  Thread.sleep(10000)

  singleThreadPoolContext.shutdown()
  fixedTreadPoolContext.shutdown()



