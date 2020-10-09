object TraitsExercise extends App{
  trait Logger{
    def log(msg: String): Unit
  }
  trait CryptoLogger extends Logger{
    var key: Int = 3
    override def log(msg: String): Unit = {
      val encryptMessage = msg.map(ch => ch + key.hashCode()).mkString("")
      println(encryptMessage)
    }
  }
  trait ConsoleLogger extends Logger{
    override def log(msg: String): Unit = {
      println(msg)
    }
  }
  trait ShortLogger extends Logger{
    val maxLength: Int = 10
    override def log(msg: String): Unit = {
      if(msg.length > maxLength) println(msg.substring(0, maxLength) + "...")
      else println(msg)
    }
  }
  class SavingsAccount(val initialBalance: Double = 0) extends CryptoLogger{
    private var _balance  = initialBalance
    def balance(): Unit = {
      if (_balance == 0) log("You have no balance")
      else {
        key = 5
        log(s"You have ${_balance} balance")
      }
    }
  }
  val saving1 = new SavingsAccount
  val saving2 = new SavingsAccount(32) with ConsoleLogger
  val saving3 = new SavingsAccount with ShortLogger
  saving1 balance()
  saving2 balance()
  saving3 balance()

}
