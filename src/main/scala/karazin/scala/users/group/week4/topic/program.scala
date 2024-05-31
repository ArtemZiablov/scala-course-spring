package karazin.scala.users.group.week4.topic

// Do not forget to import custom implementation

import cats.data.EitherT
import cats.implicits.*
import cats.syntax.all.*
import karazin.scala.users.group.week4.either.withFilter
import karazin.scala.users.group.week4.topic.model.*
import karazin.scala.users.group.week4.topic.model.Post.PostId
import karazin.scala.users.group.week4.topic.model.User.UserId
import karazin.scala.users.group.week4.topic.services.*
import sun.security.util.Password

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.{Failure, Success}

object program extends App:


  val singleThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor())

  val fixedTreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(10))

  given ExecutionContext = ExecutionContext.global

  def registrationFlow(username: String, password: String)
                      (using ExecutionContext): Result[UserProfile] =
    for
      registrationResult <- registerUser(username, password)
    yield registrationResult

  def getPostView(post: Post)(commentsFilter: List[Comment] => Boolean,
                              likesFilter: List[Like] => Boolean)
                 (using ExecutionContext): Result[PostView] =
    println(s"Main thread: ${Thread.currentThread().getName}")

    val getCommentsService = getComments(post.postId)(using fixedTreadPoolContext)
    val getLikesService = getLikes(post.postId)(using fixedTreadPoolContext)
    val getSharesService = getShares(post.postId)(using fixedTreadPoolContext)

    for
      comments  <- getCommentsService.withFilter(commentsFilter)
      likes     <- getLikesService.withFilter(likesFilter)
      shares    <- getSharesService
    yield PostView(post, comments, likes, shares)


  getPostView(Post(UserId.generate, PostId.generate))(_ => true, _ => true).value.onComplete {
    case Success(value)     => println(s"Completed with $value")
    case Failure(exception) => println(s"Failed with $exception")
  }

  Thread.sleep(10000)

  singleThreadPoolContext.shutdown()
  fixedTreadPoolContext.shutdown()



