package karazin.scala.users.group.week4.homework

import cats.data.ValidatedNec
import cats.syntax.all.*
import karazin.scala.users.group.week4.homework.errors.{DomainError, PasswordDoesNotMeetCriteria, PostalCodeDoesNotMeetCriteria,
  UsernameHasSpecialCharacters, BirthDateInvalidFormat, UserTooYoung, EmailInvalidFormat, PhoneInvalidFormat}
import karazin.scala.users.group.week4.homework.model.RegistrationData

import java.time.format.DateTimeFormatter
import scala.util.Try
import java.time.LocalDate

object validation extends App:

  type ValidationResult[A] = ValidatedNec[DomainError, A]

  def validateUsername(username: String): ValidationResult[String] =
    if username.matches("^[a-zA-Z0-9]+$") then username.validNec
    else UsernameHasSpecialCharacters.invalidNec

  def validatePassword(password: String): ValidationResult[String] =
    if password.matches("(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$") then password.validNec
    else PasswordDoesNotMeetCriteria.invalidNec

  def validateEmail(email: String): ValidationResult[String] =
    if email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") then email.validNec // borrowed from https://regexr.com/3e48o
    else EmailInvalidFormat.invalidNec

  def validatePhone(phone: String): ValidationResult[String] =
    if phone.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$") then phone.validNec // borrowed from https://regexr.com/3c53v
    else PhoneInvalidFormat.invalidNec

  def validateBirthDate(birthDate: String): ValidationResult[LocalDate] =
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    Try(LocalDate.parse(birthDate, formatter)).toOption match
      case Some(date) =>
        val sixYearsAgo = LocalDate.now().minusYears(6)
        if date.isBefore(sixYearsAgo) then date.validNec
        else UserTooYoung.invalidNec
      case None => BirthDateInvalidFormat.invalidNec

  def validatePostalCode(postalCode: String): ValidationResult[String] =  // borrowed from: https://gist.github.com/jamesbar2/1c677c22df8f21e869cca7e439fc3f5b
    if postalCode.matches("^\\d{5}$") then postalCode.validNec // ukrainian postal code format
    else PostalCodeDoesNotMeetCriteria.invalidNec

  def validateRegistrationForm(username: String, password: String, email: String,
                               phone: String, birthDate: String, postalCode: String): Either[List[DomainError], RegistrationData] =
    (validateUsername(username),
      validatePassword(password),
        validateEmail(email),
          validatePhone(phone),
            validateBirthDate(birthDate),
              validatePostalCode(postalCode))
      .mapN(RegistrationData.apply).toEither.leftMap(_.toList)


  println(validateRegistrationForm("Username", "password#1_QWERTY", "user@karazin.ua", "+380-111-222-33-44", "2007-08-27", "08200"))      // everything is valid
  println(validateRegistrationForm("User name", "password#1_QWERTY", "user@karazin.ua", "+380-111-222-33-44", "2007-08-27", "08200"))     // invalid username
  println(validateRegistrationForm("Username", "password_QWERTY", "user@karazin.ua", "+380-111-222-33-44", "2007-08-27", "08200"))        // invalid password
  println(validateRegistrationForm("Username", "password#1_QWERTY", "user@", "+380-111-222-33-44", "2007-08-27", "08200"))                // invalid email
  println(validateRegistrationForm("Username", "password#1_QWERTY", "user@karazin.ua", "+380amamam111-222-33-44", "2007-08-27", "08200")) // invalid phone
  println(validateRegistrationForm("Username", "password#1_QWERTY", "user@karazin.ua", "+380-111-222-33-44", "08.27.2007", "08200"))      // invalid birth date
  println(validateRegistrationForm("Username", "password#1_QWERTY", "user@karazin.ua", "+380-111-222-33-44", "2007-08-27", "130082042"))  // invalid postal code
  println(validateRegistrationForm("User name", "password#1 QWERTY", "user@", "+380amamam111-222-33-44", "08.27.2007", "130082042"))      // everything is invalid

