package tutorial

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}
import tutorial.MonadBagSample._


class MonadBagSampleTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val c = new Complex(1.2, 3.4)
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    val sugarBag = Bag(Sugar(1))

    assert(sugarBag.map(identity) === sugarBag)

    assert(Bag(Sugar(1)).flatMap(double) === double(Sugar(1)))
    assert(Bag.apply(Sugar(1)).flatMap(z => double(z)) == double(Sugar(1)))
    assert(Bag(Sugar(1)).flatMap(Bag.apply) == Bag(Sugar(1)))
    assert(Bag(Sugar(1)).flatMap(double).flatMap(tripple) == Bag(Sugar(1)).flatMap(x ⇒ double(x).flatMap(tripple)))
    assert(true===false)
  }

}