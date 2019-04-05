package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class FlattenTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

    val c = new Complex(1.2, 3.4)
    before {
      println("Starting")
    }

    after {
      println("Test Done")
    }

    it should "fail" in {

      println("Step 1: How to initialize a Sequence of donuts")
      val donuts1: Seq[String] = Seq("Plain", "Strawberry", "Glazed")
      println(s"Elements of donuts1 = $donuts1")

      println("\nStep 2: How to initialize another Sequence of donuts")
      val donuts2: Seq[String] = Seq("Vanilla", "Glazed")
      println(s"Elements of donuts2 = $donuts2")

      println("\nStep 3: How to create a List of donuts initialized using the two Sequences from Step 1 and Step 2")
      var listDonuts: List[Seq[String]] = List(donuts1, donuts2)
      println(s"Elements of listDonuts = $listDonuts")


      println("\nStep 4: How to return a single list of donut using the flatten function")
      val listDonutsFromFlatten: List[String] = listDonuts.flatten
      println(s"Elements of listDonutsFromFlatten = $listDonutsFromFlatten")


      println("\nStep 5: How to append the word Donut to each element of listDonuts using flatten and map functions")
      val listDonutsFromFlatten2: List[String] = listDonuts.flatten.map(_ + " Donut")
      println(s"Elements of listDonutsFromFlatten2 = $listDonutsFromFlatten2")


      assert(true===false)
    }

  }