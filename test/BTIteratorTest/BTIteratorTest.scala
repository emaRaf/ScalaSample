package BTIteratorTest

import btiterator.{BTIterator, Node}
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, GivenWhenThen, Matchers}
import tutorial.Complex

class BTIteratorTest extends FlatSpec
  with GivenWhenThen with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val iterator = new BTIterator()
  before {
    println("Starting")
  }

  after {
    println("Test Done")
  }

  it should "fail" in {

    val leaf=new Node(None,None,4)
    val p=new Node(Some(leaf),None,5)
    val p_broth=new Node(None,None,9)
    val gp=new Node(Some(p_broth),Some(p),8)
    val ggp=new Node(Some(gp),None,10)
    val root= new Node(None,Some(ggp),1)

    iterator.build(root)

    assert(iterator.next===1)
    assert(iterator.next===4)
    assert(iterator.next===5)
    assert(iterator.next===8)
    assert(iterator.next===9)
    assert(iterator.next===10)

    assert(true===false)
  }

}