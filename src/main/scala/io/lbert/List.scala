package io.lbert

sealed trait List[+A] {

  def ::[B >: A](b: B): List[B] = io.lbert.::[B](b,this)

  def foldLeft[B](b: B)(f: (B,A) => B): B = {
    def loop(accu: B, rest: List[A]): B = rest match {
      case Nil => accu
      case x :: xs => loop(f(accu,x),xs)
    }
    loop(b,this)
  }

  def map[B](f: A => B): List[B] =
    foldLeft[List[B]](Nil)((a,i) => f(i) :: a).reverse

  def reverse: List[A] =
    foldLeft[List[A]](Nil)((a,i) => i :: a)

  def headOption: Option[A] = this match {
    case head :: _ => Some(head)
    case _         => None
  }

  def length: Int = foldLeft(0)((a,_) => a + 1)
}

case object Nil extends List[Nothing]
final case class ::[B](head: B, tail: List[B]) extends List[B]

object List {

  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else as.head :: apply(as.tail: _*)
  }

  def fromScalaList[A](list: scala.List[A]): List[A] =
    list.foldLeft[List[A]](List())((a,i) => i :: a).reverse
}