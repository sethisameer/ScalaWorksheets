import java.awt.Point
object ObjectExercise extends App {
  object Conversions {
    def inchesToCentimeters(inches: Double): Double = {
      inches * 2.54
    }
    def gallonsToLiters(gallons: Double): Double = {
      gallons * 3.785
    }
    def milesToKilometers(miles: Double): Double = {
      miles * 1.609
    }
  }

  println(Conversions.inchesToCentimeters(12))
  println(Conversions.gallonsToLiters(12))
  println(Conversions.milesToKilometers(12))

  trait UnitConversions{
    def apply(v: Double):Double
  }
  object inchesToCentimeters extends UnitConversions{
    override def apply(v: Double): Double = v * 2.54
  }
  object gallonsToLiters extends UnitConversions{
    override def apply(v: Double): Double = v * 3.785
  }
  object milesToKilometers extends UnitConversions{
    override def apply(v: Double): Double = v * 1.609
  }
  println("Using Trait")
  println(Conversions.inchesToCentimeters(12))
  println(Conversions.gallonsToLiters(12))
  println(Conversions.milesToKilometers(12))

  class AwtOrigin extends java.awt.Point
  val aw = new AwtOrigin
  println(aw.x)
  aw.setLocation(30, 23) // set location is mutating x, y
  println(aw.x)

  class Point(val x: Double, y:Double)
  object Point {
    def apply(x: Double, y: Double): Point = new Point(x, y)
  }
  val p = Point(334.45, 22.32)
  println(p.x)

}

object CardType extends Enumeration{
  type CardType = Value
  val Heart, Diamond, Spade, Club = Value
}
import CardType._
object UseEnum extends App{
  def checkCardType(card: CardType) = {
    card match {
      case Heart => s"Red"
      case Diamond => s"Black"
      case Spade => s"spade"
      case Club => s"spade"
    }
  }
  println(checkCardType(Diamond))
}

object CubeColors extends Enumeration {
  type CubeColors = Value
  val Red = Value(0xff0000)
  val Green = Value(0x00ff00)
  val Blue = Value(0x0000ff)
  val Yellow = Value(0xffff00)
  val Orange = Value(0xffA500)
  val White = Value(0xffffff)
}

import CubeColors._
object DescribeCubeCorners extends App {
  CubeColors.values.foreach(c => println(f"${c.id}%06x: ${c}%s"))
}

object Weekdays extends Enumeration {
  type Weekdays = Value
  val Sun, Mon, Tue, Wed, Thu, Fri, Sat = Value
}
import Weekdays._
object Schedule extends  App {
  def planMaintainence(d: Weekdays) = {
    d match {
      case Sat => s"Run cluster stop script at 0:00"
      case Sun => s"Continue keeping clusters down its ${Sun.id} of the week"
      case _ => s"All clusters should be running"
    }
    def isWorkingDay(d: Weekdays): Boolean = {
      !(d == Sat || d == Sun)
    }
    Weekdays.values filter isWorkingDay foreach println
  }

  println(planMaintainence(Sun))
}

package ss{
  package object random {
    val defaultName = "John Woo"
  }
  package random{
    object TestPackage extends App {
      val name = defaultName
      println(name)
    }
  }
}
import java.util.{HashMap => JavaHashMap}
import scala.collection.mutable.{HashMap => ScalaHashMap}
object copyHashMapFromJava extends App {
  def copy[A, B](source:JavaHashMap[A, B], target: ScalaHashMap[A, B]) = {
    import scala.collection.JavaConverters._
    for((k, v) <- source.asScala){
      target(k) = v
    }
    target
  }
  val source: JavaHashMap[String, String] = new JavaHashMap
  source.put("Foo", "Bar")
  source.put("Baz", "Buzz")
  println(copy(source, new ScalaHashMap))
}
