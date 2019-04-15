package tutorial

object MonadBagSample {

  case class Bag[A](content: A) {
    def map[B](f: A => B): Bag[B] = Bag(f(content))
   // def apply(content: A): Bag[A] = new Bag(content)

    def flatMap[B](f: A => Bag[B]): Bag[B] = f(content)

    def flatten = content
    def identity[A](x: A): A = x
  }

  case class Sugar(weight: Double)
  // the guy who is expert at making sugar half
  def half = (sugar: Sugar) => Sugar(sugar.weight / 2)
  val sugarBag: Bag[Sugar] = Bag(Sugar(1)) //Bag functor of type sugar
  // map is the guy in our story who can perform operations
  // by unwrapping the bag and then calling respective function
  // and wraps the content back in a bag
  val halfSugarBag: Bag[Sugar] = sugarBag.map(sugar => half(sugar))


  def double = (sugar: Sugar) => Bag(Sugar(sugar.weight * 2))
  def tripple = (sugar: Sugar) => Bag(Sugar(sugar.weight * 3))

  val doubleSugarBag = sugarBag.map(sugar => double(sugar)).flatten

  val doubleSugarBag1 = sugarBag.flatMap(sugar => double(sugar))



}
