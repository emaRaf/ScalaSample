package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class ComprehensionTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    println("Step 1: A simple for loop from 1 to 5 inclusive")
    for (numberOfDonuts <- 1 to 5) {
      println(s"Number of donuts to buy = $numberOfDonuts")
    }

    println("\nStep 2: A simple for loop from 1 to 5, where 5 is NOT inclusive")
    for (numberOfDonuts <- 1 until 5) {
      println(s"Number of donuts to buy = $numberOfDonuts")
    }

    println("\nStep 3: Filter values using if conditions in for loop")
    val donutIngredients = List("flour", "sugar", "egg yolks", "syrup", "flavouring")
    for (ingredient <- donutIngredients if ingredient == "sugar") {
      println(s"Found sweetening ingredient = $ingredient")
      println("Found sweetening ingredient = " + ingredient)
    }


    println("\nStep 4: Filter values using if conditions in for loop and return the result back using the yield keyword")

    val sweeteningIngredients = for {
      ingredient <- donutIngredients
      if (ingredient == "sugar" || ingredient == "syrup")
    } yield ingredient

    println(s"Sweetening ingredients = $sweeteningIngredients")


    val twoDimensionalArray = Array.ofDim[String](2, 2)
    twoDimensionalArray(0)(0) = "flour"
    twoDimensionalArray(0)(1) = "sugar"
    twoDimensionalArray(1)(0) = "egg"
    twoDimensionalArray(1)(1) = "syrup"

    for {x <- 0 until 2
         y <- 0 until 2
    } println(s"Donut ingredient at index ${(x, y)} = ${twoDimensionalArray(x)(y)}")


    assert(true === false)
  }

}