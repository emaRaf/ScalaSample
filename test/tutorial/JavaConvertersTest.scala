package tutorial

import java.util._
import java.util.Map

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

import scala.collection.JavaConverters._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer


class JavaConvertersTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }


  /*
  Seq           =>    java.util.List
mutable.Seq   =>    java.util.List
Set           =>    java.util.Set
Map           =>    java.util.Map
   */
  it should "fail" in {
    val javaArray: List[Int] = Arrays.asList(1, 2, 3)
    val scalaArray: Seq[Int] = javaArray.asScala.map(n => n * n)

    val jul: List[Int] = ArrayBuffer(1, 2, 3).asJava
    val buf: Seq[Int] = jul.asScala
    val m: Map[String, Int] = HashMap("abc" -> 1, "hello" -> 2).asJava

/*
    val jul2 = List(1, 2, 3).asJava
    jul.add(7)
*/
    assert(true === false)
  }

}