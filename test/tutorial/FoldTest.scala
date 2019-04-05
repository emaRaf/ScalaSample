package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class FoldTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    println("Step 1: How to initialize a sequence of donut prices")
    val prices: Seq[Double] = Seq(1.5, 2.0, 2.5)
    println(s"Donut prices = $prices")

    println("\nStep 2: How to sum all the donut prices using fold function")
    val sum = prices.fold(0.0)(_ + _)
    println(s"Sum = $sum")

    println("\nStep 3: How to initialize a Sequence of donuts")
    val donuts: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
    println(s"Elements of donuts1 = $donuts")

    println("\nStep 4: How to create a String of all donuts using fold function")
    println(s"All donuts = ${donuts.fold("")((acc, s) => acc + s + " Donut ")}")

    println("\nStep 5: How to declare a value function to create the donut string")
    val concatDonuts: (String, String) => String = (s1, s2) => s1 + s2 + " Donut "
    println(s"Value function concatDonuts = $concatDonuts")

    println("\nStep 6: How to create a String of all donuts using value function from Step 5 and fold function")
    println(s"All donuts = ${donuts.fold("")(concatDonuts)}")


    assert(true===false)
  }

}