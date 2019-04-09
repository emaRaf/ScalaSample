package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class ImplicitSampleTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val implicitSample = new ImplicitSample()
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {
    val value = 10
    implicit val anotherValue = 2
  //  implicit val anotherValue2 = 3
    println(implicitSample.multiply)


    implicitSample.useImplicitMethod()

    assert(true === false)
  }

}
