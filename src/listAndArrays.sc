object listAndArrays{
  val arr = new Array[Int](4)
  for(i <- arr) println

  val mixedArray = Array("Nintendo", "Xbox", "Sony", 4)
  // Return all instance of type string and append some suffix
  for(i <- mixedArray if(i.isInstanceOf[(String)])) yield i+"_games"

  val range = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100)

//  val lessThanFive = Array[Int]()
//  val greaterThanFive = Array[Int]()
//
//  for(n <- range) yield if (n < 5) {
//     lessThanFive  :+ n
//  } else {
//    greaterThanFive :+ n
//  }
//
////  lessThanFive.foreach(println)
////
////  greaterThanFive.foreach(println)


  val myList = List(1, 2, 3)
//  myList :+ 6
//  myList.foreach(println)
  val myList1 = myList.foldLeft(4)(_ * _)
  myList1

  val range1To10 = List.range(1, 11)
  val range20To30 = List.range(20, 31)

  val listOfPair = List((1, 2), (3, 4), (5,6))

  val sumOfNetstedPair = for( (el1, el2) <- listOfPair) yield el1 + el2

  var sum = 0
  for(el <-sumOfNetstedPair) yield sum+=el
  sum
}