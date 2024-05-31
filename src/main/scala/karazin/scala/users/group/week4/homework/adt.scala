//package karazin.scala.users.group.week4.homework
//
//object adt:
//
//  // Implement ErrorOrT
//  // Prove that ErrorOrT is a monad
//
//  enum ErrorOr[+V]:
//
//    // Added to make it compilable. Remove it.
//    case DummyCase
//
//    /* 
//      Two case must be defined: 
//      * a case for a regular value
//      * a case for an error (it should contain an actual throwable)
//     */
//
//    /* 
//      The method is used for defining execution pipelines
//      Provide a type parameter, an argument and a result type
//
//      Make sure that if an internal function is failed with an exception
//      the exception is not thrown but the case for an error is returned
//    */ 
//    def flatMap = ???
//
//    /* 
//      The method is used for changing the internal object
//      Provide a type parameter, an argument and a result type
//
//      Make sure that if an internal function is failed with an exception
//      the exception is not thrown but the case for an error is returned
//     */
//    def map = ???
//
//    def withFilter(p: V => Boolean) = ???
//
//    def flatten[U](using ev: V <:< ErrorOr[U]) = ???
//
//    def foreach[U](f: V => U): Unit = ???
//
//    def fold[Q](ifEmpty: => Q)(f: V => Q): Q = ???
//
//    def foldLeft[Q](z: Q)(op: (Q, V) => Q): Q = ???
//
//    def foldRight[Q](z: Q)(op: (V, Q) => Q): Q = ???
//
//
//  // Companion object to define constructor
//  object ErrorOr:
//    /* 
//      Provide a type parameter, an argument and a result type
//
//      Make sure that if an internal function is failed with an exception
//      the exception is not thrown but the case for an error is returned
//    */
//    def apply = ???
//
