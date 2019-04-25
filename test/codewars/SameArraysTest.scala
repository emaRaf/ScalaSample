package codewars

import org.scalatest.FunSuite

class SameArraysTest extends FunSuite {
  test("Example Test") {
    val seq1 = Seq(121, 144, 19, 161, 19, 144, 19, 11)
    val seq2 = Seq(11 * 11, 121 * 121, 144 * 144, 19 * 19, 161 * 161, 19 * 19, 144 * 144, 19 * 19)
    assert(
      SameArrays.comp(seq1, seq2) == true,
      s"\ncomp(${seq1}, ${seq2}) should be true"
    )
  }

  test("Example Test Vector") {
    val seq1 = Vector(73, 39)
    val seq2 = Vector(1522, 5329)
    assert(
      SameArrays.comp(seq1, seq2) == false,
      s"\ncomp(${seq1}, ${seq2}) should be false"
    )

  }

  test("Example Test List") {
    val seq1 = List(121, 144, 19, 161, 19, 144, 19, 11)
    val seq2 = List(231, 14641, 20736, 361, 25921, 361, 20736, 361)
    assert(
      SameArrays.comp(seq1, seq2) == false,
      s"\ncomp(${seq1}, ${seq2}) should be false"
    )
  }


  test("Example Test Vector 2") {
    val seq1 = Vector(97, 34, 80, 17)
    val seq2 = Vector(1156, 6400, 290, 9409)
    assert(
      SameArrays.comp(seq1, seq2) == false,
      s"\ncomp(${seq1}, ${seq2}) should be false"
    )
  }

  test("Example Test Vector 3") {
    val seq1 = Vector(82, 94, 97, 8)
    val seq2 = Vector(8836, 9409, 6724, 65)
    assert(
      SameArrays.comp(seq1, seq2) == false,
      s"\ncomp(${seq1}, ${seq2}) should be false"
    )
  }
}