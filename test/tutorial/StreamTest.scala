package tutorial

import java.util.stream.Collectors

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

import scala.collection.immutable.Stream.cons

class StreamTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    println("Step 1: How to create a Stream with 3 numbers using #::")
    val stream1: Stream[Int] = 1 #:: 2 #:: 3 #:: Stream.empty
    println(s"Elements of stream1 = $stream1")


    println("\nStep 2: How to create a Stream with 3 numbers using Stream.cons")
    val stream2: Stream[Int] = cons(1, cons(2, cons(3, Stream.empty) ) )
    println(s"Elements of stream2 = ${stream2}")

    println("\nStep 3: How to print all 3 numbers from stream2 using the take function")
    print("Take first 3 numbers from stream2 = ")
    stream2.take(3).print

    print("\nTake first 10 numbers from stream2 = ")
    stream2.take(10).print

    println("\n\nStep 4: How to define an infinite Stream of numbers using Stream.cons")
    def inifiniteNumberStream(number: Int): Stream[Int] = Stream.cons(number, inifiniteNumberStream(number + 1))
    print("Take only the first 20 numbers from the infinite number stream = ")
    inifiniteNumberStream(1).take(20).print

    println("\n\nStep 5: How to define an infinite stream of numbers using Stream.from")
    val stream3: Stream[Int] = Stream.from(1)
    print("Take only the first 20 numbers from the infinite number stream = ")
    stream3.take(20).print

    println("\n\nStep 6: How to initialize an empty Stream")
    val emptyStream: Stream[Int] = Stream.empty[Int]
    println(s"Empty Stream = $emptyStream")







    assert(true===false)
  }

}