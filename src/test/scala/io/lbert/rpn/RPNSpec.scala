package io.lbert.rpn

import org.scalatest.{Matchers, WordSpec}
import io.lbert.{List,Some,None}
import RPN._

class RPNSpec extends WordSpec with Matchers {

  "execute" should {
    "return easy addition" in {
      RPN.execute(List(Value(1),Value(2),Operation(Add))) shouldBe Some(3)
    }
    "return complex notaion" in {
      RPN.execute(List(
        Value(10),
        Value(4),
        Value(3),
        Operation(Add),
        Value(2),
        Operation(Multiply),
        Operation(Subtract)
      )) shouldBe Some(-4)
    }
  }

  "parse" should {
    "return parsed RPN inputs" in {
      RPN.parse("3 4 *") shouldBe Some(List(Value(3),Value(4),Operation(Multiply)))
    }
    "fail if unrecongnizable operation" in {
      RPN.parse("3 4 #") shouldBe None
    }
  }

  "runRPN" should {
    "run for complex notation" in {
      RPN.runRPN("10 4 3 + 2 * -") shouldBe Some(-4)
    }
  }
}
