object OperatorsExercise extends App {

  object ExtractFullName {
    def unapply(name: String): Option[(String, String)] = {
      val pos: Int = name.indexOf(" ")
      if (pos != -1) Some((name.substring(0, pos), name.substring(pos + 1)))
      else None
    }
  }

  val user = "John Doe"
  val testExtractor = user match {
    case ExtractFullName(firstName, lastName) => s"$firstName - $lastName"
    case _ => Nil
  }
  println(testExtractor)

  // Email extractor > unapply
  object Email {
    def unapply(email: String): Option[(String, String)] = {
      val atSymbol = email.indexOf("@")
      if (atSymbol == -1) None
      else {
        val parts: (String, String) = email.splitAt(atSymbol)
        Some((parts._1, parts._2.tail))
      }
    }
  }

  val userEmail = "foobar@baz.com"
  val emailExtractor = userEmail match {
    case Email(username, domain) => s"$username - $domain"
    case _ => Nil
  }
  println(emailExtractor)

  // Uppercase extractor > unapply
  object Uppercase {
    def unapply(str: String): Option[String] = Some(str toUpperCase)
  }

  val city = "los angeles"

  case class Location(city: String, state: String)

  val uppercaseExtractor = Location("los angeles", "ca") match {
    case Location(Uppercase(city), Uppercase(state)) => s"$city, $state"
    case _ => Nil
  }
  println(uppercaseExtractor)

  // Extract from file path
  object DestructurePath {
    def unapply(path: String): Option[String] = {
      val arr = path.split("/")
      if (arr.isEmpty || arr.tail.isEmpty) Some(toKeyValuePair(arr)) else Some(toKeyValuePair(arr.tail))
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

  val destructurePath = (path: String) =>
    if (path == null || path.isEmpty) throw new UnknownError()
    else if (path == "/") s"{level: Root}"
    else
      path match {
        case DestructurePath(path) => s"$path"
        case _ => throw new UnknownError()
      }

  println(destructurePath(samplePath))
  println(destructurePath(samplePath1))
  println(destructurePath(samplePath2))
  println(destructurePath(samplePath3))


  object Positive {
    def unapply(arg: Int): Option[Int] = if (arg > 0) Some(arg) else None
  }

  def isPositive(arg: Int): Boolean = {
    arg match {
      case Positive(arg) => true
      case _ => false
    }
  }

  val positive = (num: Int) => isPositive(num)
  println(positive(-2))
  println(positive(1))
  println(positive(0))

  object Titlecase {
    def unapply(arg: String): Option[Array[String]] = {
      val arr = arg.split(" ")
      if (arr.isEmpty || arr.tail.isEmpty) Some(arr) else Some(arr.tail)
    }
  }

  def tranformTitleCase(str: Array[String]) = {
    var list: List[String] = Nil
    str.foreach(s => list = list :+ s.head.toUpper + s.tail)
    list.mkString(" ")
  }

  val toTitleCase = (str: String) => {
    if (str == null || str.isEmpty) throw new UnknownError()
    else
      str match {
        case Titlecase(str) => tranformTitleCase(str)
        case _ => throw new UnknownError()
      }
  }

  object TitleCase1 {
    def unapply(arg: String): Option[String] = arg match {
      case arg => tranformTitleCase1(arg.split(" "))
      case _ => None
    }
  }

  def tranformTitleCase1(str: Array[String]): Some[String] = {
    var list: List[String] = Nil
    str.foreach(s => list = list :+ s.head.toUpper + s.tail)
    Some(list.mkString(" "))
  }

  val toTitleCase1 = (str: String) => {
    if (str == null || str.isEmpty) throw new UnknownError()
    else
      str match {
        case TitleCase1(str) => s"$str"
        case _ => throw new UnknownError()
      }
  }

  object Titlecase2 {
    def unapply(str: String) =
      Some(str.split(" ").toList.map {
        case "" => ""
        case word => word.substring(0, 1).toUpperCase + word.substring(1)
      }.mkString(" "))
  }

  val toTitleCase2 = (str: String) => {
    if (str == null || str.isEmpty) throw new UnknownError()
    else
      str match {
        case Titlecase2(str) => s"$str"
        case _ => throw new UnknownError()
      }
  }
  println(toTitleCase("open new query console and 2 more shortcuts..."))
  println(toTitleCase1("operator exercise does not take parameters"))
  println(toTitleCase2("optional section covers some more details of the collections"))
}
