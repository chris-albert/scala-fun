package io.lbert

sealed trait Option[+A] {

  def map[B](f: A => B): Option[B] = this match {
    case Some(a) => Some(f(a))
    case _       => None
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(a) => f(a)
    case _       => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case Some(a) => a
    case _       => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case rest => rest
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) if !f(a) => Some(a)
    case _               => None
  }

  def fold[B](default: => B)(f: A => B): B = this match {
    case Some(a) => f(a)
    case _       => default
  }

}

final case class Some[+A](a: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def apply[A](a: A): Option[A] =
    if(a == null) None
    else Some(a)
}



