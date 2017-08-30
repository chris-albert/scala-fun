package io.lbert

//import akka.actor._
//import scala.concurrent.Future
//import akka.pattern.pipe

object ThrottleActor {

//  def externalSystem(): Future[Int] = Future.successful(1)
//
//  class Throttle(numConcurrent: Int) extends Actor {
//
//    import context._
//
//    def receive = work(0)
//
//    def work(currentCount: Int): Receive = {
//      case _ =>
//        if(currentCount < numConcurrent) {
//          become(work(currentCount + 1))
////          pipe(externalSystem()) to self
//        } else {
//          sender ! "oops too many connections"
//        }
//      case x =>
//        become(work(currentCount - 1))
//        sender ! x
//      case Status.Failure(x) =>
//        become(work(currentCount - 1))
//        sender ! "there was a fails"
//    }
//  }
//
//  val system = ActorSystem("throttleActorSystem")
//  val actor = system.actorOf(Props[Throttle],"throttleActor")


}
