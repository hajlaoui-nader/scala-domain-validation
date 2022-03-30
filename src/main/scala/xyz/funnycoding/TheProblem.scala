package xyz.funnycoding

import cats.implicits._
import cats.data.ValidatedNel
import io.estatico.newtype.macros._
import eu.timepit.refined.auto._
import eu.timepit.refined.api.RefinedTypeOps
import eu.timepit.refined.types.string

object TheProblem extends App {
  type PnrR = string.NonEmptyString
  object PnrR extends RefinedTypeOps[PnrR, String]

  type TcnR = string.NonEmptyString
  object TcnR extends RefinedTypeOps[TcnR, String]

  type FareCodeR = string.NonEmptyString
  object FareCodeR extends RefinedTypeOps[FareCodeR, String]

  @newtype case class Tcn(value: TcnR)
  @newtype case class Pnr(value: PnrR)
  @newtype case class FareCode(value: FareCodeR)

  case class Ticket(pnr: Pnr, tcn: Tcn, fareCode: FareCode)

  def mkTicket(t: String, p: String, fc: String): ValidatedNel[String, Ticket] =
    (
      PnrR.from(p).toValidatedNel.map(Pnr.apply),
      TcnR.from(t).toValidatedNel.map(Tcn.apply),
      FareCodeR.from(fc).toValidatedNel.map(FareCode.apply)
    ).mapN(Ticket.apply)

}
