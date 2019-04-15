package codewars

object LongestConsec {

  def generateTuple(strarr: Array[String], tupleLength: Int): String = {

    strarr.slice(0, tupleLength).reduce((x, y) => x + y)
    /*
        Range(0, tupleLength).map(i => strarr.reduce())

        println(strarr.size)
        strarr.map(s => print(s + " "))
        println("")
        0
     */
  }

  def longestConsec(strarr: Array[String], k: Int): String = {

    var maxLength = ""

    if (0 <= k && k <= strarr.length) {
      strarr.zipWithIndex.foreach {
        case (e, i) => val temp = generateTuple(strarr.slice(i, strarr.length), k)

          if (temp.length > maxLength.length) {
            maxLength = temp
          }

      }
    }


    /*
        strarr.zipWithIndex.foreach {
          case (e, i) => val temp = strarr.slice(i, k).reduce((x, y) => x + y)

            if (temp.length > maxLength.length) {
              maxLength = temp
            }

        }
    */
    /*
        strarr.reduce((x, y) => {
          println(x + y)
          x + y
        })

        strarr.reduceRight((x, y) => {
          println(x + y)
          x + y
        })

        strarr.reduceLeft((x, y) => {
          println(x + y)
          x + y
        })

        strarr.map(s => println("print"))

        for (index <- 1 to strarr.size) {


          println("looping")
        }
    */

    maxLength
  }


}