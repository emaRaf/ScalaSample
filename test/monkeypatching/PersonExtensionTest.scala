package monkeypatching

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}
import tutorial.Complex
import monkeypatching.PersonExtensions._

class PersonExtensionTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  /*
  implicit class PersonExtensionImplicit() {
    def incAgeImplicit(person: Person): Person = {
      person.copy(age = person.age + 1)
    }
  }
*/

  val person: Person = new Person("ema", 99)


  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {


    val olderPerson = PersonExtension.incAge(person)
    println(olderPerson);


    assert(true === false)
  }


  it should "fail2" in {
    val olderPerson = person.incAgeImplicit


    println(olderPerson);


    assert(true === false)
  }

  it should "fail3" in {

    val olderPerson = person.incrementAgeImplicit


    println(olderPerson);


    assert(true === false)
  }


  implicit class PersonExtensionImplicit(person: Person) {
    def incAgeImplicit = {
      println("running PersonExtensionImplicit::incAgeImplicit from implicit class PersonExtensionImplicit")
      person.copy(age = person.age + 1)
    }
  }

}