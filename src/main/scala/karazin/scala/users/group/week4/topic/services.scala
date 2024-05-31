package karazin.scala.users.group.week4.topic

import cats.data.EitherT
import cats.implicits.*
import cats.syntax.all.*
import karazin.scala.users.group.week4.topic.errors.{DomainError, DomainErrors}
import karazin.scala.users.group.week4.topic.model.Post.PostId
import karazin.scala.users.group.week4.topic.model.Result
import karazin.scala.users.group.week4.topic.model.User.UserId
import karazin.scala.users.group.week4.topic.validation

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

// Do not forget to import custom implementation
import karazin.scala.users.group.week4.topic.model.*


object services:

  // Emulation
  def registerUser(username: String, password: String)
                  (using EC: ExecutionContext): Result[UserProfile] =
    EitherT[Future, DomainErrors, UserProfile](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start user registration...")

        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `registerUser`: ${Thread.currentThread().getName}")

        Thread.sleep(2000)

        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("User registered. Or not...")

        validation.validateRegistrationForm(username, password)/*In a case of success store the user in the db*/
          .map(_ => UserProfile(UserId.generate))
      }(EC)
    )



  // Emulation
  def getUserProfile(apiKey: String)
                    (using EC: ExecutionContext): Result[UserProfile] =
    EitherT[Future, DomainErrors, UserProfile](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start getting user profile...")
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `getUserProfile`: ${Thread.currentThread().getName}")
        
        Thread.sleep(2000)
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Got user profile...")

        Either.right(UserProfile(UserId.generate))
      }(EC)
    )

  // Emulation
  def getPosts(userId: UserId)
              (using EC: ExecutionContext): Result[List[Post]] =
    EitherT[Future, DomainErrors, List[Post]](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start getting posts...")
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `getPosts`: ${Thread.currentThread().getName}")
        
        Thread.sleep(2000)
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Got posts...")

        Either.right(Post(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )

  // Emulation
  def getComments(postId: PostId)
                 (using EC: ExecutionContext):  Result[List[Comment]] =
    EitherT[Future, DomainErrors, List[Comment]](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start getting comments...")
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `getComments`: ${Thread.currentThread().getName}")
        
        Thread.sleep(2000)
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Got comments...")

        Either.right(Comment(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )

  // Emulation
  def getLikes(postId: PostId)
              (using EC: ExecutionContext): Result[List[Like]] =
    EitherT[Future, DomainErrors, List[Like]](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start getting likes...")
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `getLikes`: ${Thread.currentThread().getName}")
        
        Thread.sleep(2000)
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Got likes...")

        Either.right(Like(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )
  def getShares(postId: PostId)
               (using EC: ExecutionContext):  Result[List[Share]] =
    EitherT[Future, DomainErrors, List[Share]](
      Future {
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Start getting shares...")
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println(s"Current thread for `getShares`: ${Thread.currentThread().getName}")
        
        Thread.sleep(2000)
        
        /*
         We are using `println` for simplicity.
         Don't do this in a production ready code.
        */
        println("Got shares...")

        Either.right(Share(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )
