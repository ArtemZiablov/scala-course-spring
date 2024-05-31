package karazin.scala.users.group.week4.topic

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
