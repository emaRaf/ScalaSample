package tutorial

class MyServiceGeneric[T] {
    var items = List.empty[T]

    def pop() = items match {
      case Nil => throw new NoSuchElementException
      case x :: xs => {
        items = xs
        x
      }
    }

    def size() = items.size

    def isEmpty() = items.size == 0

    def push(item:T) = {
      items = item :: items
    }

  def applyDiscount[T2](discount: T2) {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")

      case d: Double =>
        println(s"$d discount will be applied")

      case _ =>
        println("Unsupported discount type")
    }
  }

  def applyDiscountWithReturnType[T](discount: T): Seq[T] = {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")
        Seq[T](discount)

      case d: Double =>
        println(s"$d discount will be applied")
        Seq[T](discount)

      case d @ _ =>
        println("Unsupported discount type")
        Seq[T](discount)
    }
  }

}
