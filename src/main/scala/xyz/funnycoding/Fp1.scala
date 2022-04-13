package xyz.funnycoding

object Fp1 {

  sealed trait POption[+A]
  case object PNone extends POption[Nothing]
  case class PSome[A](a: A) extends POption[A]

  implicit val pOptionFunctor = new PFunctor[POption] {
    def map[A, B](fa: POption[A])(f: A => B): POption[B] =
      fa match {
        case PNone    => PNone
        case PSome(a) => PSome(f(a))
      }
  }

  sealed trait PList[+A]
  case object PNil extends PList[Nothing]
  case class PCons[+A](head: A, tail: PList[A]) extends PList[A]

  implicit val plistFunctor = new PFunctor[PList] {
    def map[A, B](fa: PList[A])(f: A => B): PList[B] = fa match {
      case PNil        => PNil
      case PCons(h, t) => PCons(f(h), map(t)(f))
    }
  }

  trait PSemiGroup[A] {
    def combine(x: A, y: A): A
  }
  trait PMonoid[A] extends PSemiGroup[A] {
    def empty: A
  }

  trait PFunctor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait PApplicative[F[_]] extends PFunctor[F] {
    def pure[A](a: A): F[A]
    def ap[A, B](fa: F[A])(f: F[A => B]): F[B]
  }

  trait PMonad[F[_]] extends PApplicative[F] {
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
    def flatten[A](p: F[F[A]]): F[A] = flatMap(p)(identity)
  }

  trait PTraversable[F[_]] extends PMonad[F] {
    def traverse[A, B](fa: F[A])(f: A => F[B]): F[F[B]]
    def sequence[A](fas: F[F[A]]): F[F[A]] = traverse(fas)(identity)
  }

}
