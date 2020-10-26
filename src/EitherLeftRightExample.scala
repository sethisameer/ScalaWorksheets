object EitherLeftRightExample extends App{
  def divideXByY(x: Int, y: Int): Either[String, Int] = {
    if(y == 0) Left("Can't divide by 0!!")
    else Right(x/y)
  }

    divideXByY(3, 2) match {
      case Left(str) => println(s"Invalid number $str")
      case Right(num) => println(num)
    }

}
