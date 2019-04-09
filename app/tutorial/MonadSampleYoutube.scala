package tutorial


object MonadSampleYoutube {

    trait IntFunctor {
      def map(f: Int => Int): IntFunctor
    }

    trait IntMonad {
      def flatMap(f: Int => IdentityMonad): IntMonad
    }

    case class IdentityMonad(i: Int) extends IntFunctor with IntMonad {
      override def map(f: Int => Int): IdentityMonad = IdentityMonad(f(i))
      override def flatMap(f: Int => IdentityMonad): IdentityMonad = f(i)
    }

    def main(args: Array[String]) {
      println("Hello Monad!")
      val intMonad1 = IdentityMonad(1)
      val intMonad2 = intMonad1 map (_ + 1)
      val intMonad3 = intMonad2 flatMap (int => IdentityMonad(int + 1))

      // 3 + 1 + (3) = 7
      val intMonad7 = intMonad3 flatMap (three => IdentityMonad(three + 1) map (three + _))
      println(s"$intMonad1 $intMonad2 $intMonad3 $intMonad7")

      println("Now with do-while notation (for-yield in Scala is like do-while in Haskell)")
      val intMonad7redo = for {
        three <- intMonad3
        four <- IdentityMonad(three + 1)
      } yield three + four
     println(intMonad7redo)
      testStateMonad()
    }

    trait Monad[T] {
      import scala.language.higherKinds // type constructor
      type StateMonadT[_] <: Monad[_] // "<:" means "subtype or same type"
      def flatMap[B](f: T => StateMonadT[B]): StateMonadT[B]
    }

    case class StateMonad[StateT, ResultT](run: StateT => (ResultT, StateT)) extends Monad[ResultT]{
      override type StateMonadT[_] = StateMonad[StateT, _] // StateMonad[S, B] extends Monad[B] for some type B

      // Creates a new state monad that first applies run then applies f
      def map[Result2T](f: ResultT => Result2T): StateMonad[StateT, Result2T] =
        StateMonad(int => {
          val (a, newInt) = run(int) // int.ToFloat, int + 1
          (f(a), newInt)
        }
        )

      // creates a new state monad that is the first (run) followed by the second (f)
      override def flatMap[Result2T](f: ResultT => StateMonadT[Result2T]): StateMonadT[Result2T] =
      // pass in a run that increments and makes it int a float
        StateMonad(int => {
          val (a, newInt) = run(int) // int.ToFloat, int + 1
          val intermediary = f(a)  // create state monad that converts float to Double, leave int same
          intermediary.run(newInt) // run it to get the double of the original int
        }
        )
    }

    def testStateMonad(): Unit = {

      val incrementingStateMonad = StateMonad{
        (state: Int) => {
          (state.toFloat, state + 1)
        }
      }

      {
        val one = 1
        // state gets carried along on the right. Result of computation goes on the left.
        val (twoFloat, two) = incrementingStateMonad.run(one)
        val (threeFloat, three) = incrementingStateMonad.run(two)
        val (fourFloat, four) = incrementingStateMonad.run(three)
      }

      val toDoubleStateMonad = incrementingStateMonad map ( (float: Float) => float.toDouble )

      {
        val one = 1
        val (twoDouble, two) = toDoubleStateMonad.run(one)
        val (threeDouble, three) = toDoubleStateMonad.run(two)
        val (fourDouble, four) = toDoubleStateMonad.run(three)
        println(s"$twoDouble $threeDouble $fourDouble") // 1.0 2.0 3.0
          println(s"$two $three $four") // 2 3 4
      }

      // First we apply the standard function (+1), then we apply the flatMap (+10), producing (+11).
      val toDoubleFlatMapMonad = incrementingStateMonad.flatMap{
        (float: Float) => {
          StateMonad( (int: Int) => { (float.toDouble, int) } )
        }
      }

      {
        val one = 1
        val (twoDouble, two) = toDoubleFlatMapMonad.run(one)
        val (threeDouble, three) = toDoubleFlatMapMonad.run(two)
        val (fourDouble, four) = toDoubleFlatMapMonad.run(three)
        println(s"$twoDouble $threeDouble $fourDouble") // 1.0 2.0 3.0
          println( s"$two $three $four") // 2 3 4
      }
    }

}
