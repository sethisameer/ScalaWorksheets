object tuples{
  var simplePair = (1, "Hello", 42.42)
  val hello = simplePair._2.toUpperCase
  val(a, b, c) = simplePair
  c

  val symbols = Array("<", "-", ">")
  val count = Array(2, 10, 2)
  val keys = Array("a1", "a2", "a3")
  val pair = count.zip(symbols)
  for((n, s) <- pair) println(n)

  def divide10(n:Int):Tuple3[Int, Int, Int] = (n*10, n/2, n%2)
  divide10(5)
  val(hundreds, tens, ones) = divide10(99)

}