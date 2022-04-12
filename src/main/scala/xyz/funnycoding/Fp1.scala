package xyz.funnycoding

object Fp1 {

  sealed trait PList[+A]
  case object PNil extends PList[Nothing]
  case class PCons[+A](head: A, tail: PList[A]) extends PList[A]

  trait PFunctor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait PApplicative[F[_]] extends PFunctor[F] {
    def pure[A](a: A): F[A]
    def ap[A, B](fa: F[A])(f: F[A => B]): F[B]
  }

}
