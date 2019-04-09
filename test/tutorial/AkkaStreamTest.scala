package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import java.io.File


class AkkaStreamTest  extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

/*
    val stream = Source(Seq("test1.txt", "test2.txt", "test3.txt"))
      .map(new File(_))
      .filter(_.exists())
      .filter(_.length() != 0)
      .to(Sink.foreach(f => println(s"Absolute path: ${f.getAbsolutePath}")))
*/

    implicit val actorSystem = ActorSystem("system")
    implicit val actorMaterializer = ActorMaterializer()

    val source = Source(List("test1.txt", "test2.txt", "test3.txt", "README.md"))
    val mapper = Flow[String].map(new File(_))
    val existsFilter = Flow[File].filter(_.exists())
    val lengthZeroFilter = Flow[File].filter(_.length() != 0)
    val sink = Sink.foreach[File](f => println(s"Absolute path: ${f.getAbsolutePath}"))

    val stream = source
      .via(mapper)
      .via(existsFilter)
      .via(lengthZeroFilter)
      .to(sink)

    stream.run()


    assert(true===false)
  }

}