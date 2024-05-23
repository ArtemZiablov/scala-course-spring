package karazin.scala.users.group.week4.homework

object errors:

  type DomainErrors = List[DomainError]
  sealed trait DomainError:
    def errorMessage: String

  case object FilterError extends DomainError:
    def errorMessage: String = "The object doesn't meet the filter condition"

  case object UsernameHasSpecialCharacters extends DomainError:
    def errorMessage: String = "Username cannot contain special characters."

  case object PasswordDoesNotMeetCriteria extends DomainError:
    def errorMessage: String =
      """
        |Password must be at least 10 characters long, including an uppercase and a lowercase letter,
        |one number and one special character.
        |""".stripMargin

  case object EmailInvalidFormat extends DomainError:
    def errorMessage: String = "Email must be in a valid format."

  case object PhoneInvalidFormat extends DomainError:
    def errorMessage: String = "Phone number must be in a valid format."
  
  case object BirthDateInvalidFormat extends DomainError:
    def errorMessage: String = "Birth date must be in the format yyyy-MM-dd."
  
  case object UserTooYoung extends DomainError:
    def errorMessage: String = "User must be at least 6 years old."
  
  case object PostalCodeDoesNotMeetCriteria extends DomainError:
    def errorMessage: String = "Ukrainian postal code must be exactly 5 digits."
