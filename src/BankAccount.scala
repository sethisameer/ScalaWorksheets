object BankAccount extends App {
  val customerOne = Account("John Doe", (43.53, 0), "checking")
  val customerTWo = Account("Paul Bake", (242.00, 14425.03), "both")
  val customerThree = Account("Sean Hall", (0, 1544253.20), "Saving")

  val welcomeMessage = (cutomerInstance:Account) =>  println("Hi! " + cutomerInstance.toString)

  for(customer <- List(customerOne, customerTWo, customerThree))
    yield customer match {
      case Account("John Doe", (43.53, 0), "checking") => welcomeMessage(customer)
      case Account("Paul Bake",(242.00, 14425.03), "both") => welcomeMessage(customer)
      case Account("Sean Hall", (0, 1544253.20), "Saving") => welcomeMessage(customer)
      case _ => println("Account signature mismatch Error occured while fetching account info")
    }

}

case class Account(clientName: String, balance:Tuple2[Double, Double] = (0, 0), accType:String) {
  val currentAccountBalance = accType.toLowerCase match {
    case "checking" => "checking account# " +  Account.getCheckingAccountNumber + " balance is $" + balance._1
    case "saving" => "savings account# "+ + Account.getSavingAccountNumber  + " balance is $" + balance._2
    case "both" =>  "checking account# " + Account.getCheckingAccountNumber + " balance is $" + balance._1 + " and your savings account# " + Account.getSavingAccountNumber  + " balance is $" +  balance._2 + ("\nCombined balance of your accounts is $" + (balance._1 + balance._2))
    case _ => println("Account type not declared")
  }
  override def toString = clientName + ", your "  + currentAccountBalance + "\n"+"="*80
}
object Account extends Object{
  var checkingAccountNumber = 222000
  var savingsAccountNumber = 333000

  def getCheckingAccountNumber():Int = {
    checkingAccountNumber += 1

    checkingAccountNumber
  }
  def getSavingAccountNumber():Int = {
    savingsAccountNumber += 1

    savingsAccountNumber
  }
}