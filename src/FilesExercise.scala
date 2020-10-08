
object FilesExercise extends App {
  import scala.io.Source
  class ReadFromFile(val fileName: String){
    def readFromFile(): Iterator[String] = {
      val file = Source.fromFile(fileName)
      val iterator: Iterator[String] = file.getLines
      iterator.
    }
    def reverseLines(lines: Iterator[String]): Unit = {
      val reverseLines: Iterator[String] = lines.toArray.reverseIterator
      reverseLines foreach(line => println(line))
    }
  }
  val somePoem = new ReadFromFile("src/poem.txt")
  val lines: Iterator[String] = somePoem readFromFile()
  somePoem reverseLines lines
}

object WriteFileExample extends App {
  import java.io.PrintWriter
  class WriteToFile(filename: String){
    def writeLine(): Unit = {
      val file = new PrintWriter(filename)
      file append("Hello From Scala!!")
//      file.write("Hello from Scala!!!")
      file.close()
    }
  }
  val write = new WriteToFile("src/poem.txt")
  write.writeLine()
}