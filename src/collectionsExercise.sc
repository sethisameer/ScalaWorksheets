import scala.collection.{mutable}

object collectionsExercise {

  def indexes(str:String): mutable.Map[Char, mutable.Set[Int]] = {
    val map = new mutable.HashMap[Char, mutable.Set[Int]]
    for(i <- 0 until str.length)
      map.getOrElseUpdate(str(i), new mutable.LinkedHashSet[Int]) += i

    map
  }
  indexes("Mississippi")

  def indexes1(str:String): Map[Char, List[Int]] = {
    var map = Map[Char, List[Int]]()
    for(i <- 0 until str.length){
      val c = str(i)
      map = map.updated(c, map.getOrElse(c, Nil) :+ i)
    }
    map
  }

  indexes1("Mississippi")

  /**
   * Task 3:
   *
   * Write a function that removes all zeroes from a linked list of integers.
   */
  def removeZeros(l:List[Int]):List[Int] = {
//    var l2:List[Int] = Nil
//    l.foreach(i => if(i % 2 != 0) l2 = l2 :+ i
//    else l2 = l2 :+ i /10)
//
//    l2
    val l1 = l.filter(num => num != 0)
    l1
  }
  removeZeros(List(10, 0, 30, 0, 2531, 55, 0 , 56))
  /**
   * Task 4:
   *
   * Write a function that receives a collection of strings and a map from strings to integers.
   * Return a collection of integers that are values of the map corresponding to one of
   * the strings in the collection. For example, given
   * `Array("Tom", "Fred", "Harry")` and `Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)`,
   * return `Array(3, 5)`. Hint: Use flatMap to combine the Option values returned by get.
   */
  def mapToValues(arr:Array[String], map:Map[String, Int]) = {
    var a1:Array[Int] = Array()
    for((k, v) <- map if(arr.contains(k))) yield a1 = a1 :+ v
    a1
  }
  def mapToValuesUsingFlatMap(arr:Array[String], map:Map[String, Int]) = {
    arr.flatMap(map.get)
  }
  val arr = Array("Apple", "Sony", "Microsoft")
  val map = Map("Apple" -> 1, "Sony" -> 2, "Microsoft" -> 3, "Nintendo" -> 4)
  mapToValues(arr, map)
  mapToValuesUsingFlatMap(arr, map)

  /**
   * Task 5:
   *
   * Implement a function that works just like `mkString`, using `reduceLeft`.
   */
  def collectionToString[T](c:Traversable[T]):String = {
      if(c isEmpty) ""
         else c.reduceLeft((a:Any, b:T) => a + "*-*" + b).toString
  }
  collectionToString(List(1, 2, 5, 6))


  def priceCalc(prices:Array[Double], quantities:Array[Double]) = {
    val pairs = prices.zip(quantities)
    for((v, k) <- pairs) yield v * k
  }
  // Calculation using zip, map and tuple
  def priceCalcUsingTuple(prices:Iterable[Double], quantities:Iterable[Double]):Iterable[Double] = {
      prices zip quantities map Function.tupled(_ * _)
  }
  val prices = Array(1.5, 2.23, 2.54)
  val quanties = Array(1.0, 2.5, 12.25)
  priceCalc(prices, quanties)
  priceCalcUsingTuple(prices, quanties)
}