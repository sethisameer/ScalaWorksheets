object highOrderFunction {
  println("test")
  val double = (i:Int) => i*2
  def highOrderFunc = (x:Int, y:Int => Int) => y(x)
  highOrderFunc(4, double)
  def add = (x:Int, y:Int) => x + y
  def add1(x:Int, y:Int):Int = x + y

  val subtract = (x:Int, y:Int) => x - y
  def substract1(x:Int, y:Int) = x - y

  def greet = (msg:String) => "Hello " + msg

  def abs(x:Int):Int = if (x < 0) -x  else x

  val abs1 = (x:Int) => if (x < 0) -x  else x

  // Unit Function with no return type i.e void
  def unitFunction(msg:String){
    println("Hello "+ msg)
  }

  def sumOfArgs(args:Int*) = {
    var result = 0
    for(arg <- args) result += arg
    result
  }

  add(4, 3)
  add1(x = 3, y = 4)
  subtract(4, 3)
  substract1(y = 4, x = 3)
  abs(-3)
  abs1(-3)

  unitFunction("World")

  sumOfArgs(5, 4, 8)
}
