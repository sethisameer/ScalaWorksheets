// Ref Essenstial Scala chapter 7
import scala.math.Ordering
import scala.math.Ordering.Implicits.infixOrderingOps
object TypeClassExamples extends App{
  val minOrder: Ordering[Int] = Ordering.fromLessThan[Int]((A, B) => A < B)
  val maxOrdering: Ordering[Int] = Ordering.fromLessThan[Int]((A, B) => A > B)
  implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan[Int]((A, B) => Math.abs(A) < Math.abs(B))

  final case class Rational(numerator: Int, denominator: Int)
  implicit val rationOrdering:Ordering[Rational] = Ordering.fromLessThan[Rational]((A, B) => (A.numerator.toDouble / A.denominator.toDouble) < (B.numerator.toDouble / B.denominator.toDouble))


  val list:List[Int] = List(2, 5, 4, 3, 1, 19, 13, 6)
  val minSortedList: List[Int] = list.sorted
  val maxSortedList: List[Int] = list.sorted(maxOrdering)
  val absSortedList: List[Int] = List(-4, -1, 0, 2, 3).sorted
  val absSortedList1: List[Int] = List(-4, -3, -2, -1).sorted
  val sortRational: List[Rational] = List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted

  println(list)
  println(minSortedList)
  println(maxSortedList)
  println(absSortedList)
  println(absSortedList1)
  println(sortRational)

}
// order by price, unit based on Ordering type trait
object ShoppingOrderExample extends App{
  final case class GamesOrder(units: Int, unitPrice: Double) {
    val totalPrice: Double = units * unitPrice
  }
  object GamesOrder{
    implicit val orderByPrice: Ordering[GamesOrder] = Ordering.fromLessThan[GamesOrder]((A, B) => A.totalPrice < B.totalPrice)
  }
  object OrderByUnitOrdering{
    implicit val orderByUnit: Ordering[GamesOrder] = Ordering.fromLessThan[GamesOrder]((A, B) => A.units < B.units)
  }
  object OrderByUnitPriceOrdering{
    implicit val orderByUnitPrice: Ordering[GamesOrder] = Ordering.fromLessThan[GamesOrder]((A, B) => A.unitPrice < B.unitPrice)
  }
  val ordersListSortedByPrice: List[GamesOrder] = List(GamesOrder(23, 29), GamesOrder(10, 39), GamesOrder(3, 42), GamesOrder(45, 59)).sorted
  val ordersListSortedByUnitPrice: List[GamesOrder] = List(GamesOrder(23, 29), GamesOrder(10, 39), GamesOrder(3, 42), GamesOrder(45, 59)).sorted(OrderByUnitOrdering.orderByUnit)
  val ordersListSortedByUnits: List[GamesOrder] = List(GamesOrder(23, 29), GamesOrder(10, 39), GamesOrder(3, 42), GamesOrder(45, 59)).sorted(OrderByUnitPriceOrdering.orderByUnitPrice)

  println(s"ordersListSortedByPrice $ordersListSortedByPrice")
  println(s"ordersListSortedByUnitPrice $ordersListSortedByUnitPrice")
  println(s"ordersListSortedByUnits $ordersListSortedByUnits")
}

object TypeClassPatterExample extends App{
  case class User(name: String, email: String)

  trait Equal[T]{
    def emailEqual(item1: T, item2: T): Boolean
  }
  object EmailImplicit{
    implicit object EmailEqual extends Equal[User]{
      def emailEqual(item1: User, item2: User): Boolean = item1.email == item2.email
    }
  }
  object EmailAndNameImplicit{
    implicit object EmailAndNameEqual extends Equal[User]{
      def emailEqual(item1: User, item2: User): Boolean = item1.email == item2.email && item1.name == item2.name
    }
  }

  object Eq{
    def apply[T](v1: T, v2: T)(implicit emailEqual: Equal[T]): Boolean = emailEqual.emailEqual(v1, v2)
  }

  object ImplicitUsageExamples{
    def emailImplicitUsage(userTuple: Tuple2[User, User]): Boolean = {
      import EmailImplicit._
      Eq(userTuple._1, userTuple._2)
    }
    def emailNameImplicitUsage(userTuple: Tuple2[User, User]): Boolean = {
      import EmailAndNameImplicit._
      Eq(userTuple._1, userTuple._2)
    }
    val user1: User = User("Foo", "foo@gmail.com")
    val user2: User = User("Bar", "foo@gmail.com")
    val users: List[User] = List(user1, user2)
//    println(Eq[User](user1, user2))
    println(emailImplicitUsage(Tuple2(user1, user2)))
    println(emailNameImplicitUsage(Tuple2(user1, user2)))
  }
  ImplicitUsageExamples

}


