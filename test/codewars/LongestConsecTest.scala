package codewars

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}
import LongestConsecTest._
import org.scalatest.Assertions._

class LongestConsecTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(Array("zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"), 2, "abigailtheta");
    testing(Array("ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"), 1, "oocccffuucccjjjkkkjyyyeehh");
    testing(Array(), 3, "");

  }
}

object LongestConsecTest {
  private def testing(s: Array[String], k: Int, expect: String): Unit = {
    println("Testing: " + s.mkString(",") + ", " + k)
    val actual: String = LongestConsec.longestConsec(s, k)
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("-")
    assertResult(expect) {
      actual
    }
  }
}
