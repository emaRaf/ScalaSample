package tutorial

class Complex(real: Double, imaginary: Double) {

  def newMethod() {

  }


  def re = real
  def im = imaginary
  override def toString() =
    "" + re + (if (im < 0) "-" else "+") + im + "i"
}
