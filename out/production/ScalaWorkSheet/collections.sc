object collections{
  val v = Vector(3, 4, 5);
  v.head
  v.tail

  // Append
  val a = v :+ 6
  // Append multiple
  val v1 = Vector(1, 2) ++ v

  val seq = Seq(-2, -1, 0)

  // Converting collection from Sequence to Vector
  val v2 = (seq.tail ++ a).map(_.toString).toVector

  val l1 = (8 to 25).toList

  val v3 = l1.toVector

  v3.dropRight(10)
  v3.drop(10)

  val numberLine = (-10 to 10).toVector

  // Optional way to return all numbers excluding 0
  val numLine1 = for (n <- numberLine if(n != 0)) yield n

  val (negative, positive) = numberLine.filter(n => n != 0) partition(_ < 1)
  negative
  positive

  val anotherWaytoExpressList = 2 :: 4 :: 6 :: Nil
  anotherWaytoExpressList

  val l2 =  8 :: 10 :: 12 :: anotherWaytoExpressList

  def sumOfList(l:List[Int]):Int = {
    if(l == Nil) 0
    else l.head + sumOfList(l.tail)
  }
  sumOfList((0 to 10).toList)

  def sumOfListUsingPatternMatching(l:List[Int]):Int = {
    l match {
      case Nil => 0
      case h :: t => h + sumOfListUsingPatternMatching(t)
    }
  }

  sumOfListUsingPatternMatching((10 to 20).toList)

  // list has inbuild method Sum!!
  l2.sum

  val l3 = 3 +: l2 // prepend element
  val l4 = l2 :+ 3 // append element
  val l5 = l2 ++ l3 ++ l4 // containing elements of all 3 collections (list)
  val l6 = l2 ::: l3 ::: l4 // same as above

  val set1 = (0 to 10).toSet
  val set2 = (5 to 15).toSet
  val union = set1 | set2
  val intersect = set1 & set2
  val difference = set1 &~ set2 // same as set1.diff(set2)
  val difference1 = set2 &~ set1
  val difference2 = set1 -- set2

  val parTest = for(i <- (0 until 100).par) yield i + " "
  parTest

}