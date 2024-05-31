package karazin.scala.users.group.week4.topic

import cats.data.ValidatedNec
import cats.syntax.all.*
import karazin.scala.users.group.week4.topic.errors.{DomainError, PasswordDoesNotMeetCriteria, UsernameHasSpecialCharacters}
import karazin.scala.users.group.week4.topic.model.RegistrationData

object validation:

  type ValidationResult[A] = ValidatedNec[DomainError, A]

  def validateUsername(username: String): ValidationResult[String] =
    if username.matches("^[a-zA-Z0-9]+$") then username.validNec
    else UsernameHasSpecialCharacters.invalidNec

  def validatePassword(password: String): ValidationResult[String] =
    if password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$") then password.validNec
    else PasswordDoesNotMeetCriteria.invalidNec

  def validateRegistrationForm(username: String, password: String): Either[List[DomainError], RegistrationData] =
    (validateUsername(username),
      validatePassword(password)).mapN(RegistrationData.apply).toEither.leftMap(_.toList)
