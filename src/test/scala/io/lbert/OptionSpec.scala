package io.lbert

import org.scalatest.{Matchers, WordSpec}

class OptionSpec extends WordSpec with Matchers {

  "Some" should {
    "be an Option" in {
      Some("hi") shouldBe a[Option[_]]
    }
    "run map function" in {
      Some("hi").map(_ + " there") shouldBe Some("hi there")
    }
    "run flatMap function" in {
      Some("hi").flatMap(s => Some(s"$s there")) shouldBe Some("hi there")
    }
    "get value for getOrElse" in {
      Some("hi").getOrElse("bye") shouldBe "hi"
    }
    "not return orElse" in {
      Some("hi").orElse(Some("bye")) shouldBe Some("hi")
    }
    "not filter if predicate fails" in {
      Some("hi").filter(_ == "bye") shouldBe Some("hi")
    }
    "filter if predicate succeeds" in {
      Some("hi").filter(_ == "hi") shouldBe None
    }
    "run function on fold" in {
      Some("hi").fold[String]("bye")(_ + " there") shouldBe "hi there"
    }
  }

  "None" should {
    "be an Option" in {
      None shouldBe a[Option[_]]
    }
    "not run map function" in {
      None.map(_ => "maybe") shouldBe None
    }
    "not run flatMap function" in {
      None.flatMap(_ => Some("maybe")) shouldBe None
    }
    "get else value in getOrElse" in {
      None.getOrElse("bye") shouldBe "bye"
    }
    "return orElse" in {
      None.orElse(Some("bye")) shouldBe Some("bye")
    }
    "always return None for filter" in {
      None.filter(_ == "hi") shouldBe None
    }
    "get default on fold" in {
      None.fold("hi")(_ => "there") shouldBe "hi"
    }
  }

  "Option" should {
    "return None if given null" in {
      Option(null) shouldBe None
    }
    "return Some if given value" in {
      Option(1) shouldBe Some(1)
    }
  }
}
