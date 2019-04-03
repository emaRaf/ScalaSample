package tutorial
import org.scalatest._

class HelloWorldTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val helloWorld = new HelloWorld
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {
    helloWorld.method(Array("arg1"))
    assert(true===false)
  }

}
