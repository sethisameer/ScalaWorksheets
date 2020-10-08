// Sacala for Impatient Chapter 8 (Inheritance exercises)
object InheritanceExercise extends App {
  class BankAccount(initialBalance: Double){
    private var balance: Double = initialBalance
    def currentBalance:Double = balance
    def deposit(amt: Double): Unit = { balance += amt}
    def withdraw(amt: Double): Unit = { balance -= amt}
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    override def deposit(amt: Double): Unit = {
      super.deposit(amt - 1)
    }

    override def withdraw(amt: Double): Unit = {
      super.withdraw(amt + 1)
    }
  }

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance){
    private var transactionCount: Int = 0
    def earnMonthlyInterest(rateOfInterest: Double) = {
      if(transactionCount == 3)
        super.deposit(this.currentBalance * rateOfInterest)
      transactionCount = 0
    }

    override def deposit(amt: Double): Unit = {
       val cost = if (transactionCount > 3) 1 else 0
       super.deposit(amt - cost)
      transactionCount += 1
    }

    override def withdraw(amt: Double): Unit = {
      val cost = if (transactionCount > 3) 1 else 0
      super.withdraw(amt + cost)
      transactionCount += 1
    }
  }

  println("Base class==========")
  val b1 = new BankAccount(0)
  b1.deposit(20)
  b1.withdraw(5)
  println(b1.currentBalance)

  println("Checking Account ==========")
  val b2 = new CheckingAccount(0)
  b2.deposit(20)
  println(b2.currentBalance)
  b2.withdraw(5)
  println(b2.currentBalance)

  println("Saving Account==========")
  val b3 = new SavingsAccount(0)
  b3.deposit(10)
  b3.withdraw(5)
  b3.deposit(10)
  b3.deposit(10)
  b3.deposit(10)
  b3.earnMonthlyInterest(.02)
  b3.deposit(10)
  b3.deposit(20)
  b3.deposit(20)
  b3.deposit(20)
  println(b3.currentBalance)
}

object ItemsExample extends App {
  abstract class Item{
    def price: Double
    def description: String
  }
  class SimpleItem(override val price: Double, override val description: String) extends Item {}
  class Bundle extends Item{
    val items = scala.collection.mutable.ArrayBuffer[Item]()

    override def price: Double = {
      items.map(_.price) sum
    }
    override def description: String = {
      s"Bundle has ${items.size} items"
    }
    def add(item: Item): Unit = {
      items += item
    }
    def details(): Unit = {
      items foreach(item => println(s"${item.price} ${item.description}"))
    }
  }

  val b1 = new Bundle
  b1.add(new SimpleItem(10, "Item One"))
  b1. add(new SimpleItem(23, "Item Two"))
  b1. add(new SimpleItem(47, "Item Three"))
  b1 details()
}

object PointExample extends App {
  class Point(val x: Double, val y: Double)
  class LabelPoint(val label:String, x: Double, y: Double) extends Point(x, y){
    override def toString: String = {
      s"${label} coordiantes ${x}, ${y}"
    }
  }
  val labelPoint = new LabelPoint("Los Angeles", 42.44, 55.242)
    println(labelPoint toString)
}

object ShapeExample extends App {
  import PointExample.Point
  abstract class Shape{
    def centerPoint: Point
  }
  class Circle(r: Double, pt: Point) extends Shape{
    override def centerPoint: Point = pt
  }
  class Square(size: Double, pt: Point) extends Shape{
    override def centerPoint: Point = pt
  }
  val c1 = new Circle(20, new Point(10, 20))
  val s1 = new Square(50, new Point(30, 34))
}

object SquareExample extends App {
  import java.awt.Rectangle
  class Square(x: Int, y: Int, size: Int) extends Rectangle(x, y, size, size){
    def this(size: Int) = this(0, 0, size)
    def this() = this(0, 0, 0)

    override def toString: String = s"Squar[$x, $y, $size, $size]"
  }
  val s1 = new Square(5, 20, 20)
  println(s1 toString)
}

object PersonExample extends App {
  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }
  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don’t want to reveal name . . .
    override val toString = "secret" // . . . or class name
  }
  println(new SecretAgent("Foo") toString)
}

object CreaturExample extends App {
  class Creature {
    def range: Int = 10
    def env: Array[Int] = new Array[Int](range)
  }
  class Ant extends Creature {
    override def range = 2
  }
  val c = new Creature
  println(c.range)
  println(c.env)
  val a = new Ant
  println(a.range)
  println(a.env)
}