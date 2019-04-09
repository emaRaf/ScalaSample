package tutorial

class MonadSample {


  sealed trait Term

  case class Con(c: Int) extends Term

  case class Div(l: Term, r: Term) extends Term

  object Evaluator {
    val answer = Div(Div(Con(1972), Con(2)), Con(23))
    val error = Div(Con(1), Con(0))
  }


  trait Monad[F[_]] {
    def unit[A](a: A): F[A]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  }

  trait Monad2[F[_]] {
    def flatMap[A, B](fa: F[A])(f: (A) => F[B]): F[B]
    def pure[A](x: A): F[A]
  }

  object VariationZeroBasicEval {

    // type M a = a
    type Id[A] = A

    implicit val idMonad = new Monad[Id] {
      def unit[A](a: A): Id[A] = a

      def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = f(fa)
    }

    def eval[F[_]](term: Term)(implicit M: Monad[F]): F[Int] = term match {
      case Con(c) => M.unit(c)
      case Div(t, u) => M.flatMap(eval(t)(M)) { a =>
        M.flatMap(eval(u)(M)) { b =>
          M.unit(a / b)
        }
      }
    }

    def run(): Unit = {
      val result: Id[Int] = eval(Evaluator.answer)
      println(result) // 42
    }

  }


  object VariationOneExceptions {

    // We piggyback on Either.
    // Left and Right take the role of Raise and Return.
    // data M a = Raise Exception | Return a
    // type Exception = String
    type Exceptional[A] = Either[String, A]

    trait MonadException[F[_]] extends Monad[F] {
      def raise[A](ex: String): F[A]
    }

    implicit val exceptionalMonad = new MonadException[Exceptional] {
      def unit[A](a: A): Exceptional[A] = Right(a)

      def flatMap[A, B]
      (fa: Exceptional[A])
      (f: A => Exceptional[B]): Exceptional[B] = fa match {
        case Left(e) => Left(e)
        case Right(a) => f(a)
      }

      def raise[A](ex: String): Exceptional[A] = Left(ex)
    }

    def eval
    (term: Term)
    (implicit M: MonadException[Exceptional]): Exceptional[Int] = term match {
      case Con(c) => M.unit(c)
      case Div(t, u) => M.flatMap(eval(t)(M)) { a =>
        M.flatMap(eval(u)(M)) { b =>
          // We replace: `M.unit(a / b)` with
          if (b == 0)
            M.raise("divide by zero")
          else
            M.unit(a / b)
        }
      }
    }

    def run(): Unit = {
      val resultSuccess: Exceptional[Int] = eval(Evaluator.answer)
      println(resultSuccess) // Right(42)
      val resultFailure: Exceptional[Int] = eval(Evaluator.error)
      println(resultFailure) // Left(divide by zero)
    }

  }




  def example(): Int = {
    val maybeFirstName:Option[String] = Some("Joe")
    val maybeLastName:Option[String] = Some("Black")
/*
    val maybeFullName = maybeFirstName.flatMap { firstName =>
      maybeLastName.map { lastName =>
        firstName + " " + lastName
      }
    }

*/

    val maybeFullName2 = for {
      firstName <- maybeFirstName
      lastName <- maybeLastName
    } yield println(firstName + " " + lastName)



    10
  }


}
