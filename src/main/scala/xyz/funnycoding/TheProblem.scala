package xyz.funnycoding
import cats.implicits._

object TheProblem extends App {

  case class Username private (val value: String) extends AnyVal

  case class Email private (val value: String) extends AnyVal

  def mkUsername(value: String): Option[Username] =
    (value.nonEmpty).guard[Option].as(Username(value))

  def mkEmail(value: String): Option[Email] =
    (value.contains("@")).guard[Option].as(Email(value))

  val username = Username("nader")
  println(username)

  sealed abstract case class PnrStrong(value: String)

  object PnrStrong {
    def apply(value: String): Option[PnrStrong] =
      if (value.nonEmpty) new PnrStrong(value) {}.some
      else None
  }

  println(PnrStrong(""))
}
