package io.lbert

import akka.actor._
import scala.concurrent.duration._

object ActorPi extends App {
//object ActorPi {//extends App {

//  calculate(4,1000,10000)

  sealed trait PiMessage
  case object Calculate extends PiMessage
  case class Work(start: Int, nrOfElements: Int) extends PiMessage
  case class Result(value: Double) extends PiMessage
  case class PiApproximation(pi: Double, duration: Duration)

  class Listener extends Actor {
    def receive = {
      case PiApproximation(pi,duration) =>
        println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
          .format(pi, duration))
        context.system.stop(self)
    }
  }

  class Master(nrOfWorkers: Int,
               nrOfMessages: Int,
               nrOfElements: Int,
               listener: ActorRef) extends Actor {

    var pi: Double = _
    var nrOfResults: Int = _
    val start: Long = System.currentTimeMillis()

    val workerRouter = context.actorOf(
//      Props[Worker].withRouter(RoundRobinRouter(nrOfWorkers)), name = "workerRouter"
      Props[Worker], name = "workerRouter"
    )

    def receive = {
      case Calculate =>
        for (i <- 0 until nrOfMessages) workerRouter ! Work(i * nrOfElements,nrOfElements)
      case Result(value) =>
        pi += value
        nrOfResults += 1
        if(nrOfResults == nrOfMessages) {
          listener ! PiApproximation(pi, duration = (System.currentTimeMillis() - start).millis)
          context.stop(self)
        }
    }
  }

  class Worker extends Actor {

    def calulatePiFor(start: Int, nrOfElements: Int): Double = {
      var acc = 0.0
      for (i ← start until (start + nrOfElements))
        acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
      acc
    }

    def receive = {
      case Work(start, nrOfElements) =>
        sender ! Result(calulatePiFor(start, nrOfElements))
    }
  }

//  def calculate(nrOfWorkers: Int,
//                nrOfElement: Int,
//                nrOfMessages: Int): Unit = {
//
//    val system = ActorSystem("PiSystem")
//
//    val listener = system.actorOf(Props[Listener],name = "listener")
//
//    val master = system.actorOf(Props(new Master(
//      nrOfWorkers,nrOfMessages,nrOfElement,listener
//    )),name = "master")
//
//    master ! Calculate
//  }

}


