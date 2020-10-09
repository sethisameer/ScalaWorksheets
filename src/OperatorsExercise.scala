object OperatorsExercise extends App{
  object ExtractFullName{
    def unapply(name: String) = {
      val pos:Int = name.indexOf(" ")
      if(pos != -1) Some((name.substring(0, pos), name.substring(pos + 1)))
      else None
    }
  }
  val user = "John Doe"
  val testExtractor = user match {
    case ExtractFullName(firstName, lastName) => s"${firstName} - ${lastName}"
    case _ => Nil
  }
  println(testExtractor)

  object Email{
    def unapply(email: String): Option[(String, String)] = {
      val atSymbol = email.indexOf("@")
      if(atSymbol == -1) None
      else{
        val parts: (String, String) = email.splitAt(atSymbol)
        Some((parts._1, parts._2.tail))
      }
    }
  }
  val userEmail = "foobar@baz.com"
  val emailExtractor = userEmail match {
    case Email(username, domain) => s"${username} - ${domain}"
    case _ => Nil
  }
  println(emailExtractor)
}
