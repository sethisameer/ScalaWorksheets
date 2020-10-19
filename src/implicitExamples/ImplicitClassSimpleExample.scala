package implicitExamples

/*
  * Implicit class to globally add custom string getter
  * usage: <string>.upperCaseWords => returns list of upper case words in string
 */
object ImplicitClassSimpleExample {
  implicit class StringEnhancement(str:String){
    def upperCaseWords: Option[Array[String]] = {
      if(str == null || str.isEmpty){
        None
      } else {
        val strList: Array[String] = str.split(" ")
          val filter = strList.filter(_.head.isUpper)
          if(filter.nonEmpty) Option(filter) else None
      }
    }
  }
}

object testUsage extends App{
  import ImplicitClassSimpleExample.StringEnhancement
  def findUpperCaseWords(str: String): String = str.upperCaseWords match {
    case Some(words) => words.mkString(" ")
    case None => s"No upper case found"
  }
  val sampleString = "With Markdown, you get a live preview of your formatted text inside your compose box as you type, and you can always undo your formatting by pressing Ctrl+Z."
  val sampleString1 = "you get a live preview of your formatted text inside your compose box as you type."

  println(findUpperCaseWords(sampleString))
  println(findUpperCaseWords(sampleString1))
  println(findUpperCaseWords(""))

  // Alternate simple usage
//  println(sampleString.upperCaseWords)
//  println(sampleString1.upperCaseWords)
}