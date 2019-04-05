package tutorial

import java.util.{Date, Locale}
import java.text.DateFormat._

import java.util.{Date, Locale}
import java.text.DateFormat._


class HelloWorld {
  def method(args: Array[String]) {
    println("Hello, world!")
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
    println((1).+(((2).*(3))./(1)))
  }
}


object HelloWorldApp {
  def main(args: Array[String]) {
    println("Hello, world!")
  }
}