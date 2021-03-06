object arrayExercise extends App {
  val arr = Array(40, 3, 24, 35, 332, 36)

  def sumOfArray(arr:Array[Int]):Double = {
    var sum = 0
    for(n <- arr) yield sum += n
    sum
  }

  def findAverageOfNumbers(arr:Array[Int]):Double = {
    var sum = sumOfArray(arr)
    val size = arr.length
    sum / size
  }

  val min = (arr:Array[Int]) => {
    var currentMin = arr(0)
    for(num <- arr) yield if(num < currentMin) currentMin = num
    currentMin
  }

  val max = (arr:Array[Int]) => {
    var currentMax = arr(0)
    for(num <- arr) yield if(num > currentMax) currentMax = num
    currentMax
  }

  println("Average of array is: "+ findAverageOfNumbers(arr))
  println("Min of array is: "+ min(arr))
  println("Max of array is: "+ max(arr))


  def vowelsWithHashCodes() = {
    val items = Vector("a", "e", "i", "o", "u")
    val itemsWithHash = items.map(item => (item, item.hashCode()))
    itemsWithHash
  }
  def hashOfChar(input: String) = {
    val vowels = vowelsWithHashCodes.filter(c => c._1 == input)
    if(vowels.nonEmpty)
      vowels.head._2
    else
      s"Invalid input ${input} is not a vowel"
  }
  hashOfChar("a")
}
