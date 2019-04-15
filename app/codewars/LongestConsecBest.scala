package codewars

object LongestConsecBest {

  def longestConsec1(strings: Array[String], k: Int): String = {
    if (strings.size == 0 || k > strings.size || k <= 0) "" else strings.sliding(k).map(_.mkString).maxBy(_.length)
  }

  def longestConsec2(strarr: Array[String], k: Int): String = {
    if (strarr.isEmpty || (k > strarr.length) || k <= 0) ""
    else
      strarr.sliding(k)
        .toList
        .map(_.fold("")(_ + _))
        .maxBy(_.length)
  }

  def longestConsec3(strarr: Array[String], k: Int): String = {
    if (strarr.length == 0 || k > strarr.length || k <= 0) { return "" }
    0.to(strarr.length - k).map { i => strarr.slice(i, i+k).mkString("") }.maxBy(_.length)
  }

  def longestConsec4(strarr: Array[String], k: Int): String = {
    if (strarr.isEmpty || k <= 0 || k > strarr.length) ""
    else {
      (for (subset <- strarr.sliding(k)) yield subset.flatten)
        .maxBy(_.length)
        .mkString
    }
  }

  def longestConsec5(strarr: Array[String], k: Int): String = {
    val n = strarr.length
    if (n==0 | k>n | k<=0) ""
    else {
      strarr.sliding(k).map(_.reduce(_+_)).reduce((a, b) => if (a.length >= b.length) a else b)
    }
  }

}