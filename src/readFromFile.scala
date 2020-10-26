import scala.io.Source._
object readFromFile extends App {
  val gamesListFile = "src/GameConsoles.txt"
  for(line <- fromFile(gamesListFile).getLines()) {
    println(line.toLowerCase())
  }

  val poemFile = "src/poem.txt"
  val poemLines = fromFile(poemFile).getLines().toList
  poemLines.foreach(println)

  val rainboFile = "src/rainbow.txt"
  val rainbowLines = fromFile(rainboFile).getLines().toList
  val convertCase = for(line <- rainbowLines) println(line.toUpperCase())
  convertCase
}