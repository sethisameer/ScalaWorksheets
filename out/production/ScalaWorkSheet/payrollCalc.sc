object payrollCalc{
  val rate = 10.25

  def calculatePayroll(hour:Double, payType:String):String = {
    var printSal: String = "Payroll is: "
    if(payType == "hourly")
      if(hour <= 40)
        printSal += s"${ hour * rate}"
      else
        printSal += s"${ hour * rate + (hour - 40) * rate * 1.5}"

    printSal
  }
  calculatePayroll(32, "hourly")
  calculatePayroll(42, "hourly")
}