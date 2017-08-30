package io.lbert

import org.scalatest.{Matchers, WordSpec}

class TreeSpec extends WordSpec with Matchers {

  "size" should {
    "be empty tree if called with no params" in {
      Tree().size shouldBe 0
    }
    "have 1 element" in {
      Leaf(1).size shouldBe 1
    }
    "have many elements" in {
      Branch(Branch(Leaf(1),Leaf(2)),Leaf(3)).size shouldBe 3
    }
  }

  "map" should {
    "map over nothing if empty" in {
      Empty.map(_ => "hi") shouldBe Empty
    }
    "map over ints" in {
      Branch(Branch(Leaf(1),Leaf(2)),Leaf(3)).map(_ + 1) shouldBe Branch(Branch(Leaf(2),Leaf(3)),Leaf(4))
    }
  }

  "add" should {
    "add element to empty" in {
      Empty.add(1) shouldBe Leaf(1)
    }
    "add element to leaf" in {
      Leaf(1).add(2) shouldBe Branch(Leaf(1),Leaf(2))
    }
    "add element to branch" in {
      Branch(Leaf(1),Leaf(2)).add(3) shouldBe Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))
    }
  }

  "toList" should {
    "turn to list" in {
//      Branch(Leaf(1), Leaf(2)).add(3).toList shouldBe List(1, 2, 3)
    }
  }

  "apply" should {
    "build tree" in {
//      Tree(1,2,3) shouldBe Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))
    }
  }
}
