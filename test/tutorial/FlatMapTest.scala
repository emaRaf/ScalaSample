package tutorial



import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}

class FlatMapTest extends FlatSpec
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
      val donuts1: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
      println(s"Elements of donuts1 = $donuts1")

      println("\nStep 2: How to initialize another Sequence of donuts")
      val donuts2: Seq[String] = Seq("Vanilla Donut", "Glazed Donut")
      println(s"Elements of donuts2 = $donuts2")

      println("\nStep 3: How to create a List of donuts initialized using the two Sequences from Step 1 and Step 2")
      val listDonuts: List[Seq[String]] = List(donuts1, donuts2)
      println(s"Elements of listDonuts = $listDonuts")


      println("\nStep 4: How to return a single list of donut using the flatMap function")
      val listDonutsFromFlatMap: List[String] = listDonuts.flatMap(seq => seq)
      println(s"Elements of listDonutsFromFlatMap as a flatMap as a single list = $listDonutsFromFlatMap")




      assert(true===false)
    }

  }