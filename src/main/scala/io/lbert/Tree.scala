package io.lbert


sealed trait Tree[+A] {

  def size: Int = this match {
    case Empty => 0
    case Leaf(_) => 1
    case Branch(l,r) => l.size + r.size
  }

  def foldLeft[B](b: B)(f: (B,A) => B): B = {
    def loop(accu: B, rest: Tree[A]): B = rest match {
      case Empty       => accu
      case Leaf(a)     => f(accu,a)
      case Branch(l,r) => loop(accu,l)
    }
    loop(b,this)
  }

  def map[B](f: A => B): Tree[B] = {
    def loop(accu: Tree[B], rest: Tree[A]): Tree[B] = rest match {
      case Empty       => accu
      case Leaf(a)     => accu.add(f(a))
      case Branch(l,r) => Branch(loop(accu,l),loop(accu,r))
    }
    loop(Empty,this)
  }

  def add[B >: A](b: B): Tree[B] = this match {
    case Empty            => Leaf(b)
    case Leaf(a)          => Branch(Leaf(a),Leaf(b))
    case br @ Branch(_,_) => Branch(br,Leaf(b))
  }

  def toList: List[A] = ???

}
case class Leaf[A](elem: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
case object Empty extends Tree[Nothing]

object Tree {
  def apply[A](elems: A*): Tree[A] =
    if(elems.isEmpty) Empty
    else apply(elems.tail:_*).add(elems.head)
}
