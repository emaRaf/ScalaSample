package btiterator

import scala.collection.mutable.ListBuffer

class BTIterator {

  //  var nodes: Seq[Node] = Seq[Node]()
  var nodes = new ListBuffer[Node]()
  var current: Node = null

  def build(root: Node) = {

    visit(Some(root))

    println(nodes.map(n => n.key) + " ")

    nodes = nodes.sortBy(node => node.key)
    println(nodes.map(n => n.key))

  }

  def next() = {

    if (current == null) {
      current = nodes(0)
    }
    else {
      val index = nodes.indexOf(current) + 1
      current = nodes(index)
    }

    current.key
  }


  def visit(node: Option[Node]): Unit = {

    if (node.isEmpty) {
      return
    }

    val nodeValue: Node = node.get
    nodes.append(nodeValue)

    visit(nodeValue.right)
    visit(nodeValue.left)
  }

}
