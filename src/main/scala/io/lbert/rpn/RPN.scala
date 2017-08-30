package io.lbert.rpn

import io.lbert.{::, List, Nil, None, Option, Some}
import scala.util.{Success, Try}

object RPN {

  sealed trait RPNOperation {
    def calc(l: Int, r: Int): Int
  }
  case object Add extends RPNOperation {
    def calc(l: Int, r: Int): Int = l + r
  }
  case object Subtract extends RPNOperation {
    def calc(l: Int, r: Int): Int = l - r
  }
  case object Multiply extends RPNOperation {
    def calc(l: Int, r: Int): Int = l * r
  }

  sealed trait RPNInput
  case class Value(i: Int) extends RPNInput
  case class Operation(operation: RPNOperation) extends RPNInput

  def parse(input: String): Option[List[RPNInput]] = {
    def loop(accu: List[RPNInput], rest: List[String]): Option[List[RPNInput]] = rest match {
      case Nil => Some(accu)
      case "*" :: rest => loop(Operation(Multiply) :: accu, rest)
      case "+" :: rest => loop(Operation(Add) :: accu, rest)
      case "-" :: rest => loop(Operation(Subtract) :: accu, rest)
      case n :: rest => Try(n.toInt) match {
        case Success(i) => loop(Value(i) :: accu, rest)
        case _          => None
      }
    }
    loop(List(),List.fromScalaList(input.split(" ").toList)).map(_.reverse)
  }

  def execute(input: List[RPNInput]): Option[Int] = {
    def loop(stack: List[Int], operations: List[RPNInput]): Option[Int] = operations match {
      case Value(v)      :: rest => loop(v :: stack,rest)
      case Operation(op) :: rest => stack match {
        case l :: r :: stackRest => loop(op.calc(r,l) :: stackRest,rest)
        case _ => None
      }
      case _ => if(stack.length > 1) None else stack.headOption
    }
    loop(List(),input)
  }

  def runRPNEither(in: String): Either[String,Int] =
    runRPN(in).fold[Either[String,Int]](Left("Error in RPN calc"))(Right(_))

  def runRPN(in: String): Option[Int] =
    for {
      parsed <- parse(in)
      out    <- execute(parsed)
    } yield out
}
