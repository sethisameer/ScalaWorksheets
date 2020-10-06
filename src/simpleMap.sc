object simpleMap{
  val gamesUniverse = Map(
    1 -> "Sony",
    2 -> "Microsoft",
    3 -> "Nintendo",
    4 -> "Nintendo Switch",
    5 -> "Nintendo 3ds",
    6 -> "Nintendo 2ds",
    7 -> "Nintendo wii",
    8 -> "Nintendo wii u"
  )
for( value <- gamesUniverse.values) println(value.toUpperCase)
  gamesUniverse.get(2)
  gamesUniverse(2)
  gamesUniverse.getOrElse(4, "No match found")
  gamesUniverse.+(5 -> "Foo")
  val nintendo = for((key, value) <- gamesUniverse if value.startsWith("Nintendo")) yield value.toUpperCase

  val l = 1 to 10
    l.map(arg => arg * arg)
  val lowerCaseChars = Seq('a', 'b', 'c', 'd')

  val upperCaseMap = lowerCaseChars.map(ch => ch.toUpper)
  val upperLowerCase = lowerCaseChars ++ upperCaseMap

  // flatmap combines two functions into one first it Mapt then it flattens
  val upperLowerCaseChars = lowerCaseChars.flatMap(char => List(char.toUpper, char))

}