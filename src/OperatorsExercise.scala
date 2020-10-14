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

  // Email extractor > unapply
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

  // Uppercase extractor > unapply
  object Uppercase{
    def unapply(str: String): Option[String] = Some(str toUpperCase)
  }

  val city = "los angeles"
  case class Location(val city: String, val state: String)

  val uppercaseExtractor = Location("los angeles", "ca") match {
    case Location(Uppercase(city), Uppercase(state)) => s"${city}, ${state}"
    case _ => Nil
  }
  println(uppercaseExtractor)

  // Extract from file path
  object DestructurePath {
    def unapply(path: String): Option[Array[String]] = {
      val arr = path.split("/")
      if (arr.isEmpty || arr.tail.isEmpty) Some(arr) else Some(arr.tail)
    }
    // Alternate approach
//    def unapply(path: String): Option[Array[String]] = if(path.indexOf("/") == -1) Some(Some(Array(path))).flatten else Some(path.tail.split("/"))
  }

  // output should be: (level0:Library, level1:Java, level(n):value(n))
  def toKeyValuePair(pathCollection: Array[String]) = {
    pathCollection.zipWithIndex
      .map(a => s"level${a._2} : ${a._1}")
      .mkString("{", ", ", "}")
  }

  val samplePath = "/home/cay/readme.txt"
  val samplePath1 =
    "/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home/bin/java"
  val samplePath2 = "test"
  val samplePath3 = "/"

  val destructurePath: String = samplePath3 match {
    case ""                    => throw new UnknownError()
    case "/"                    => s"{Level0: Root}"
    case DestructurePath(path) => toKeyValuePair(path)
    case _                     => throw new UnknownError()
  }

  val destructurePath1 = (path: String) =>
    if (path == null || path.isEmpty) throw new UnknownError()
    else
      path match {
        case DestructurePath(path) => toKeyValuePair(path)
        case _                     => throw new UnknownError()
      }

   println(destructurePath)

  object Positive {
    def unapply(arg: Int): Option[Int] = if(arg > 0) Some(arg) else None
  }

  def isPositive(arg: Int): Boolean = {
    arg match {
      case Positive(arg) => true
      case _ => false
    }
  }

}
