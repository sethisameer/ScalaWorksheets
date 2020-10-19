object GenericsExample extends App{
  // Sum type pattern is a or
  sealed trait LinkedList[T]{
    def contains[T](item: T): Boolean = this match {
      case Pair(head, tail) => if(head == item) true else {
       tail.contains(item)
      }
      case End() => false
    }
  }
  final case class End[T]() extends LinkedList[T]
  final case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]

  val example: Pair[Int] = Pair(1, Pair(2, Pair(3, End())))
  println(example.contains(4))
//  assert(example.tail.length == 2)
//  assert(End().length == 0)
}
