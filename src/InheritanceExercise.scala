object InheritanceExercise{
  object BankInheritanceExample{
    class BankAccount(initialBalance: Double){
      private var balance: Double = initialBalance
      def currentBalance:Double = balance
      def deposit(amt: Double): Unit = { balance += amt}
      def withdraw(amt: Double): Unit = { balance -= amt}
    }
    class BankAccountWithSurcharge extends BankAccount(0) {
      override def deposit(amt: Double): Unit = {
        super.deposit(amt)
        this.currentBalance - 1
      }

      override def withdraw(amt: Double): Unit = {
        super.withdraw(amt)
        this.currentBalance - 1
      }
    }
    val b1 = new BankAccount(10)
    b1.deposit(20)
    b1.withdraw(5)
    println(b1.currentBalance)
    val b2 = new BankAccountWithSurcharge()
    b1.deposit(20)
    b1.withdraw(5)
    println(b1.currentBalance)
  }
}