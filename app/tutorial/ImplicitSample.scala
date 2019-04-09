package tutorial

import scala.concurrent.Future

class ImplicitSample {

  val value = 10


  def multiply(implicit param1: Int, param2: Int): Int = {
    param1 * param2
  }

  implicit def doubleToInt(d: Double) = {
    d.toInt
  }

  def useImplicitMethod() = {
    val x : Int = 20.23
    println(x)
  }


}
