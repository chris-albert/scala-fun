package io.lbert.iot

import akka.actor.ActorSystem
import scala.io.StdIn

object IotApp extends App {

  println("Hi Iot")

  val system = ActorSystem("iot-system")

  try {
    val supervisor = system.actorOf(IotSupervisor.props(),"iot-supervisor")
    StdIn.readLine()
  } finally {
    system.terminate()
  }
}
