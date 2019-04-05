package tutorial

class Timer {
  def oncePerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  def timeFlies() {
    println("time flies like an arrow...")
  }

  def method(args: Array[String]) {
    oncePerSecond(() =>
      println("time flies like an arrow..."))
  }
}

