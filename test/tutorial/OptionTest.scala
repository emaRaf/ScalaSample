package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class OptionTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val helloWorld = new HelloWorld
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    println("capitals.get( \"France\" ) : " +  show(capitals.get( "France" )))
    println("capitals.get( \"India\" ) : " +  show(capitals.get( "India" )))

    val a:Option[Int] = Some(5)
    val b:Option[Int] = None

    println("a.getOrElse(0): " + a.getOrElse(0) )
    println("b.getOrElse(10): " + b.getOrElse(10) )
    println("b.isEmpty: " + b.isEmpty )

    assert(true===false)
  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

}
