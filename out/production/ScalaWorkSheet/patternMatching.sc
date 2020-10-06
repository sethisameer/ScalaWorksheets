object patternMatching{
  def colorCode(color:String) =
    color match {
      case "red" | "Red" => "#ff0000"
      case "green" | "Green" => "#00ff00"
      case "blue" | " Blue" => "#0000ff"
      case _ => println("color code node found")
    }

  colorCode("green")

  def max(x:Int, y:Int) =
    x > y match {
      case true => x
      case false => y
    }
  max(0, 40)
}