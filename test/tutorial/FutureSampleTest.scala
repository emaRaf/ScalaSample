package tutorial

import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext._


class FutureSampleTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val futureSample = new FutureSample()
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    val vanillaDonutStock = Await.result(futureSample.donutStock("vanilla donut"), 5 seconds)
    println(s"Stock of vanilla donut = $vanillaDonutStock")

    futureSample.donutStock("vanilla donut").onComplete {
      case Success(stock) => println(s"Stock for vanilla donut callback = $stock")
      case Failure(e) => println(s"Failed to find vanilla donut stock, exception = $e")
    }
    Thread.sleep(3000)


    println("\nStep 3: Chaining Futures using flatMap")
    val buyingDonuts: Future[Boolean] = futureSample.donutStock("plain donut").flatMap(qty => futureSample.buyDonuts(qty))

    val isSuccess = Await.result(buyingDonuts, 5 seconds)
    println(s"Buying vanilla donut was successful = $isSuccess")


    println("\nStep 3: Chaining Futures using for comprehension")
    for {
      stock <- futureSample.donutStock("vanilla donut")
      isSuccess <- futureSample.buyDonuts(stock)
    } yield println(s"Buying vanilla donut was successful = $isSuccess")

    Thread.sleep(3000)


    println("\nStep 3: Chaining Future Option using for comprehension")
    for {
      someStock <- futureSample.donutStockOption("vanilla donut")
      isSuccess <- futureSample.buyDonuts(someStock.getOrElse(0))
    } yield println(s"Buying vanilla donut was successful = $isSuccess")

    println(s"\nStep 2: Access value returned by future using map() method")
    futureSample.donutStockOption("vanilla donut")
      .map(someQty => println(s"Buying ${someQty.getOrElse(0)} vanilla donuts"))

    println(s"\nStep 3: Calling map() method over multiple futures")
    val resultFromMap: Future[Future[Boolean]] = futureSample.donutStockOption("vanilla donut")
      .map(someQty => futureSample.buyDonuts(someQty.getOrElse(0)))
    Thread.sleep(1000)


    println(s"\nStep 4: Calling flatMap() method over multiple futures")
    val resultFromFlatMap: Future[Boolean] = futureSample.donutStockOption("vanilla donut")
      .flatMap(someQty => futureSample.buyDonuts(someQty.getOrElse(0)))
    Thread.sleep(1000)

    println("\nStep 4: Combine future operations into a List")
    val futureOperations: List[Future[Any]] = List(futureSample.donutStock("vanilla donut"), futureSample.buyDonuts(10), futureSample.processPayment())

    println(s"\nStep 2: Create a List of future operations")
    val futureOperationsList = List(
      futureSample.donutStockOption("vanilla donut"),
      futureSample.donutStockOption("plain donut"),
      futureSample.donutStockOption("chocolate donut"),
      futureSample.donutStockOption("vanilla donut")
    )

    println(s"\nStep 5: Call Future.sequence to run the future operations in parallel")
    val futureSequenceResults = Future.sequence(futureOperationsList)
    futureSequenceResults.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e) => println(s"Error processing future operations, error = ${e.getMessage}")
    }


    println(s"\nStep 3: Call Future.traverse to convert all Option of Int into Int")
    val futureTraverseResult = Future.traverse(futureOperationsList) { futureSomeQty =>
      futureSomeQty.map(someQty => someQty.getOrElse(0))
    }
    futureTraverseResult.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e) => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println(s"\nStep 3: Call Future.foldLeft to fold over futures results from left to right")
    val futureFoldLeft = Future.foldLeft(futureOperationsList)(0){ case (acc, someQty) =>
      acc + someQty.getOrElse(0)
    }
    futureFoldLeft.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println(s"\nStep 3: Call Future.reduceLeft to fold over futures results from left to right")
    val futureReduceLeft = Future.reduceLeft(futureOperationsList){ case (acc, someQty) =>
      acc.map(qty => qty + someQty.getOrElse(0))
    }
    futureReduceLeft.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println(s"\nStep 3: Call Future.firstCompletedOf to get the results of the first future that completes")
    val futureFirstCompletedResult = Future.firstCompletedOf(futureOperations)
    futureFirstCompletedResult.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println(s"\nStep 3: Zip the values of the first future with the second future")
    val donutStockAndPriceOperation = futureSample.donutStock("vanilla donut") zip futureSample.donutPrice()
    donutStockAndPriceOperation.onComplete {
      case Success(results) => println(s"Results $results")
      case Failure(e)       => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println(s"\nStep 3: Define a value function to convert Tuple (Option[Int], Double) to Tuple (Int, Double)")
    val qtyAndPriceF: (Option[Int], Double) => (Int, Double) = (someQty, price) => (someQty.getOrElse(0), price)

    println(s"\nStep 4: Call Future.zipWith and pass-through function qtyAndPriceF")
    val donutAndPriceOperation = futureSample.donutStockOption("vanilla donut").zipWith(futureSample.donutPrice())(qtyAndPriceF)
    donutAndPriceOperation.onComplete {
      case Success(result) => println(s"Result $result")
      case Failure(e)      => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    println("\nStep 3: Call method which returns a Future")
    val donutStockOperation = futureSample.donutStock("vanilla donut")
    donutStockOperation.onComplete {
      case Success(donutStock) => println(s"Results $donutStock")
      case Failure(e) => println(s"Error processing future operations, error = ${e.getMessage}")
    }

    Thread.sleep(3000)

    println("\nStep 2: Execute donutStock() future operation")
    futureSample.donutStockWithThrow("vanilla donut")
      .onComplete {
        case Success(donutStock)  => println(s"Results $donutStock")
        case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
      }

    println("\nStep 3: Call Future.recover to recover from a known exception")
    futureSample.donutStockWithThrow("unknown donut")
      .recover { case e: IllegalStateException if e.getMessage == "Out of stock" => 0 }
      .onComplete {
        case Success(donutStock)  => println(s"Results $donutStock")
        case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
      }

    println("\nStep 3: Call Future.recoverWith to recover from a known exception")
    futureSample.donutStock("unknown donut")
      .recoverWith { case e: IllegalStateException if e.getMessage == "Out of stock" => Future.successful(0) }
      .onComplete {
        case Success(donutStock)  => println(s"Results $donutStock")
        case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
      }

    println("\nStep 3: Call Future.fallbackTo")
    val donutStockOperation2 = futureSample.donutStockWithThrow("plain donut")
      .fallbackTo(futureSample.similarDonutStock("vanilla donut"))
      .onComplete {
        case Success(donutStock)  => println(s"Results $donutStock")
        case Failure(e)           => println(s"Error processing future operations, error = ${e.getMessage}")
      }


    println(s"\nStep 2: Define a Promise of type Int")
    val donutStockPromise = Promise[Int]()

    println("\nStep 3: Define a future from Promise")
    val donutStockFuture = donutStockPromise.future
    donutStockFuture.onComplete {
      case Success(stock) => println(s"Stock for vanilla donut = $stock")
      case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
    }


    println("\nStep 4: Use Promise.success or Promise.failure to control execution of your future")
    val donut = "vanilla donut"
    if(donut == "vanilla donut") {
      donutStockPromise.success(futureSample.donutStockInt(donut))
    } else {
      donutStockPromise.failure(Try(futureSample.donutStockInt(donut)).failed.get)
    }

    println("\nStep 5: Completing Promise using Promise.complete() method")
    val donutStockPromise2 = Promise[Int]()
    val donutStockFuture2 = donutStockPromise2.future
    donutStockFuture2.onComplete {
      case Success(stock) => println(s"Stock for vanilla donut = $stock")
      case Failure(e)     => println(s"Failed to find vanilla donut stock, exception = $e")
    }
    donutStockPromise2.complete(Try(futureSample.donutStockInt("unknown donut")))



    assert(true === false)
  }



}