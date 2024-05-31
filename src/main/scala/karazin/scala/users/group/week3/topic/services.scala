package karazin.scala.users.group.week3.topic

import karazin.scala.users.group.week3.Monad
import karazin.scala.users.group.week3.topic.model.Post.PostId
import karazin.scala.users.group.week3.topic.model.User.UserId

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

// Do not forget to import custom implementation
import karazin.scala.users.group.week3.topic.adt.*
import karazin.scala.users.group.week3.topic.model.*


object services:

  // Emulation
  def getUserProfile(apiKey: String)
                    (using EC: ExecutionContext): OptionT[Future, UserProfile] =
    OptionT[Future, UserProfile](
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

        Option(UserProfile(UserId.generate))
      }(EC)
    )

  // Emulation
  def getPosts(userId: UserId)
              (using EC: ExecutionContext): OptionT[Future, List[Post]] =
    OptionT[Future, List[Post]](
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

        Option(Post(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )

  // Emulation
  def getComments(postId: PostId)
                 (using EC: ExecutionContext):  OptionT[Future, List[Comment]] =
    OptionT[Future, List[Comment]](
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

        Option(Comment(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )

  // Emulation
  def getLikes(postId: PostId)
              (using EC: ExecutionContext):  OptionT[Future, List[Like]] =
    OptionT[Future, List[Like]](
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
        
        Option(Like(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )
  def getShares(postId: PostId)
               (using EC: ExecutionContext):  OptionT[Future, List[Share]] =
    OptionT[Future, List[Share]](
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

        Option(Share(UserId.generate, PostId.generate) :: Nil)
      }(EC)
    )
