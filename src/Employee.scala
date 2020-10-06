object Employee extends  App {
  val emp1 = new ServicesEmployee("John Doe", 1, "Marketing")
  val emp2 = new ServicesEmployee("Zack Scott", 3, "Media")
  val emp3 = new ServicesEmployee("Roy Del", 3, "Sales")
  val emp4 = new ServicesEmployee("Sean C", 3, "Claasified®")

  val employees = for(employee <- List(emp1, emp2, emp3, emp4)) yield employee.team match {
    case "Marketing" => println(s"${employee.name} works in products promotion")
    case "Media" => println(s"${employee.name} demonstrates and record Game streams")
    case "Sales" => println(s"${employee.name} sells game apps!!!")
    case _ => println(s"${employee.name} works in unknown team")
  }
  employees
}

abstract class Employee {
  def profile():String
}

case class ServicesEmployee(name:String, employeeId:Int, team:String) extends Employee {
  def profile():String = {
      val info:String = s"Name:$name Id:$employeeId Team:$team"
      info
  }

  override def toString: String = {
    s"Employee details: $profile"
  }
}