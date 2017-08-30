package io.lbert

object Fibonacci {

  def compute(count: Int): List[Int] = {
    def loop(accu: List[Int], l: Int, r: Int, rest: Int): List[Int] = rest match {
      case 0 => accu
      case _ =>
        val next = l + r
        loop(next :: accu,r,next,rest - 1)
    }
    loop(List(1,1),1,1,count).reverse
  }
}
