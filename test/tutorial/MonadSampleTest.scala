package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class MonadSampleTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val monadSample = new MonadSample()
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    monadSample.example();

    assert(true===false)
  }

}