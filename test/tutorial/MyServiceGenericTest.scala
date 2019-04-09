package tutorial

import org.scalatest._

  class MyServiceGenericTest extends FlatSpec
    with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

    val emptyStack = new MyServiceGeneric[String]
    before {
      println("Starting")
    }

    after {
      println("Test Done")
    }

    override def beforeAll() = {
      println("Starting Whole Suite")
    }

    override def afterAll() = {
      println("End whole suite")
    }

    behavior of "A MyService"

    it should "throw NoSuchElementException if an empty stack is popped" in {
      Given("an empty stack")
      When("trying to pop")
      Then("should throw NoSuchElementException")
      intercept[NoSuchElementException] {
        emptyStack.pop()
      }
    }

    it should "have size 0 when first instantiated" in {
      emptyStack shouldBe empty
    }

    it should "pop values in last-in-first-out order" in {
      val stack = new MyServiceGeneric[Int]
      stack.push(1)
      assert(stack.size === 1)
      stack.push(2)
      assert(stack.size === 2)
      assert(stack.pop() === 2)
      assert(stack.size === 1)
      assert(stack.pop() === 1)
      assert(stack.size === 0)
      assertResult(0) { stack.size }
    }

    it should "fail" in {
      println("\nStep 2: Review how to call a function which has typed parameters")
      emptyStack.applyDiscount[String]("COUPON_123")
      emptyStack.applyDiscount[Double](10)

      println("\nStep 4: How to call a generic typed function which also has a generic return type")
      println(s"Result of applyDiscountWithReturnType with String parameter = ${emptyStack.applyDiscountWithReturnType[String]("COUPON_123")}")

      println()
      println(s"Result of applyDiscountWithReturnType with Double parameter = ${emptyStack.applyDiscountWithReturnType[Double](10.5)}")

      println()
      println(s"Result of applyDiscountWithReturnType with Char parameter = ${emptyStack.applyDiscountWithReturnType[Char]('U')}")



        assert(true === false)
      }

    ignore should "some dumb behavior we're ignoring" in { assert(0 == 1) }
    it should "some dumb behavior that's pending" in (pending)
  }