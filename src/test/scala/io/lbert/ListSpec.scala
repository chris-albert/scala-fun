package io.lbert

import org.scalatest.{Matchers, WordSpec}

class ListSpec extends WordSpec with Matchers {

  "::" should {
    "be a List" in {
      ::("foo",Nil) shouldBe a[List[_]]
    }
    "prepend an element" in {
      val list = List(1,2,3,4)
      ::(0,list) shouldBe List(0,1,2,3,4)
    }
  }

  "Nil" should {
    "be a list" in {
      Nil shouldBe a[List[_]]
    }
  }

  "List" should {
    "create list when calling apply" in {
      List(1,2,3,4) shouldBe List(1,2,3,4)
      ::(1,::(2,::(3,::(4,Nil)))) shouldBe List(1,2,3,4)
      List(1,2,3,4) shouldBe ::(1,::(2,::(3,::(4,Nil))))
    }
    ":: to prepend an element" in {
      val list = List(1,2,3,4)
      (0 :: list) shouldBe List(0,1,2,3,4)
    }
    "map over list" in {
      List(1,2,3,4).map(_ + 1) shouldBe List(2,3,4,5)
    }
    "map nothing over Nil" in {
      Nil.map(_ => 1) shouldBe Nil
    }
    "pattern match with ::" in {
      val result = List(1,2) match {
        case Nil => "not this"
        case x :: xs => "this"
      }
      result shouldBe "this"
    }
    "reverse" in {
      List(1,2,3,4).reverse shouldBe List(4,3,2,1)
    }
  }

}
