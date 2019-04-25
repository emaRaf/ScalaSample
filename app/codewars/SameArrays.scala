package codewars

object SameArrays {
  def comp(seq1: Seq[Int], seq2: Seq[Int]): Boolean = {

    if (seq1 == null || seq2 == null) {
      return false
    }
    if (seq1.length != seq2.length) {
      return false
    }

    var seq1Sorted = seq1.sorted
    var seq2Sorted = seq2.sorted


    Range(0, seq1Sorted.length).toStream.filter(i => {
      println("comparing " + seq1Sorted(i) * seq1Sorted(i) + " " + seq2Sorted(i))
      seq1Sorted(i) * seq1Sorted(i) != seq2Sorted(i)
    }).sum == 0
  }
}
