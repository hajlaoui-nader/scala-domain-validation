package xyz.funnycoding

object Fp1 {

  sealed trait PList[+A]
  case object PNil extends PList[Nothing]
  case class PCons[+A](head: A, tail: PList[A]) extends PList[A]

}
