package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class FuturesTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    assert(true===false)
  }

}