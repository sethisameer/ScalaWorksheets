object BankAccount1 extends App {
  class BankAccount1(){
    private var _balance: Long = 0L
    def balance: Double = _balance

    def withdraw(amt: Long): Unit = {
      if(_balance < Long.MinValue + amt) throw  new ArithmeticException(s"Your have negative balance (underflow) ${balance}")
      _balance -= amt
    }
    def deposit(amt: Long): Unit = {
      if(_balance > Long.MaxValue - amt) throw new ArithmeticException(s"balance exceeds allowed range (overflow)")
      _balance += amt
    }
  }
  val acc1 = new BankAccount1
  acc1.deposit(15)
  acc1.deposit(22)
  acc1.withdraw(50)
//  acc1.deposit(10540)
  println(acc1.balance)

  class Time(private val hours: Int = 0, private val minutes: Int = 0){
    private val _minutes = (hours * 60) + minutes
    def before(other: Time) = {
      _minutes < other.minutes
    }
  }
  val t = new Time(2, 24)
  println(t.before(new Time(10, 24)))
}
