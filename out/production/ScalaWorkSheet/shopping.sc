// Simple shopping exercise
// Case class with companion object using apply()
object shopping {
  final case class ShoppingCartItem(name: String, price: Double, quantity: Int)
  val items = List(
    ShoppingCartItem("Vanilla Ice-cream", 2.99, 10),
    ShoppingCartItem("Chocolate buiscuits", 1.99, 3),
    ShoppingCartItem("Banana Muffin", 1.20, 12)
  )
  for (item <- items)
    yield println(s"${item.quantity} ${item.name} at ${item.price} each")

  // val vanillaIceCream = for (item <- items) item.name match {
  //   case "Vanilla Ice-cream" => println("Found Vanilla Ice-cream!!")
  //   case _                   => println("Found another item")
  // }
  // vanillaIceCream
  def printIceCream(cartItems: List[ShoppingCartItem]): Unit = {
    for (item <- cartItems) item match {
      case ShoppingCartItem("Vanilla Ice-cream", _, _) =>
        println("Found Vanilla Ice-cream!!")
      case ShoppingCartItem(_, _, _) => println("Found another item")
    }
  }
  printIceCream(items)

  // Companion object with apply() on class
  class BasketValidator() {
    def validate[T](item: T): Unit = {
      item match {
        case item: String => println(s"Found a valid item = ${item}")
        case item: Double =>
          println(s"Item ${item} of type Double is not valid.")
        case item: Int =>
          println(s"Item ${item} should be removed from the basket.")
        case _ => println(s"Item ${item} of type Int is not valid.")
      }
    }
  }
  object BasketValidator {
    def apply(): BasketValidator = new BasketValidator()
  }
  val basketItems = List("Cupcake", 2.99, 100L, 7, "Ice Cream")
  basketItems.foreach(BasketValidator().validate(_))

  // Partial Function

  def nameIsJohn: PartialFunction[String, Boolean] = { case "John" => true }

  def nameIsJoe: PartialFunction[String, Boolean] = { case "Joe" => true }

  def nameIsJill: PartialFunction[String, Boolean] = { case "Jill" => true }

  def someOtherName: PartialFunction[String, Boolean] = { case _ => false }

  val validator = nameIsJohn orElse nameIsJoe orElse nameIsJill orElse someOtherName
  println(s"Name John is valid = ${validator("John")}")
  println(s"Name Joe is valid = ${validator("Joe")}")
  println(s"Name Jill is valid = ${validator("Jillly")}")
  println(s"Name Jiffy is valid = ${validator("Jiffy")}")
}
