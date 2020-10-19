package traitExamples

object ColorExample extends App{
  sealed trait Color{
    // Abstract methods
    def red:Double
    def green: Double
    def blue: Double

    // Concrete methods
    def isLightColor = red + green + blue < 100
    def isDarColor = !isLightColor
  }

  case object Red extends Color{
    override def red: Double = 1.0
    override def green: Double = 0
    override def blue: Double = 0
  }

  case object Green extends Color{
    override def red: Double = 1.0
    override def green: Double = 1.0
    override def blue: Double = 0
  }

  case object Blue extends Color{
    override def red: Double = 0
    override def green: Double = 0
    override def blue: Double = 1.0
  }

  final case class CustomColor(red: Double, green: Double, blue: Double) extends Color

  sealed trait Shape{
    def sides: Int
    def perimeter: Double
    def area: Double
    def color: Color
  }

  final case class Circle(radius: Double, color: Color) extends Shape{
    val sides: Int = 1
    val perimeter: Double = 2 * math.Pi * radius
    val area: Double = math.Pi * radius * radius
  }

  sealed trait Rectangular extends Shape{
    def width: Double
    def height: Double

    // Concrete methods
    override val sides = 4
    override val perimeter: Double = 2 * width + 2 * height
    override val area: Double = width * height
  }

  final case class Square(size: Double, color: Color) extends Rectangular{
    val width = size
    val height = size
  }
  final case class Rectangle(width: Double, height: Double, color: CustomColor) extends Rectangular

  object Draw{
    def apply(shape: Shape): String = shape match {
      case Circle(radius, color) => s"A ${Draw(color)} circle of radius ${radius}cm"
      case Square(size, color) =>  s"A ${Draw(color)} square of size ${size}cm"
      case Rectangle(width, height, color) => s"A ${Draw(color)} rectangle of width ${width}cm and height ${
        height}cm"
    }

    def apply(color: Color): String = color match {
      case Red => Red.red.toString
      case Green => "green"
      case Blue => "blue"
      case _ => s"Custom color $color"
    }
  }
  val d1 = Draw(Circle(45, Red))
  val d2 = Draw(Rectangle(45, 45, CustomColor(120.0, 130.0, 33.0)))
  println(d1)
  println(d2)
  println(d1)
}
