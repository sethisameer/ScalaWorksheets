package implicitExamples

import scala.collection.mutable.ArrayBuffer
/*
  * Implicit class to globally add custom string getter
  * usage: <string>.upperCaseWords => returns list of upper case words in string
 */
object ImplicitClassSimpleExample {
  implicit class StringEnhancement(str:String){
    def upperCaseWords: Option[String] = {
      val list: ArrayBuffer[String] = ArrayBuffer()
      val strList: Array[String] = str.split(" ")
      strList.foreach(word => if(word.head.isUpper) list += word)
      if(list.isEmpty) None else Some(list.toList.mkString(","))
    }
  }
}
object testUsage extends App{
  import ImplicitClassSimpleExample.StringEnhancement
  def findUpperCaseWords(str: String): String = {
    str.upperCaseWords match {
      case Some(word) => word
      case None => s"No upper case found"
    }
  }
  val sampleString = "With Markdown, you get a live preview of your formatted text inside your compose box as you type, and you can always undo your formatting by pressing Ctrl+Z."
  val sampleString1 = "you get a live preview of your formatted text inside your compose box as you type."

  println(findUpperCaseWords(sampleString))
  println(findUpperCaseWords(sampleString1))

  println(sampleString.upperCaseWords)
  println(sampleString1.upperCaseWords)
}
