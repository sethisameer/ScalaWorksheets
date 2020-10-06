object Vehicle{
  abstract class Vehicle(val make: String)
  case class Car(override val make: String) extends Vehicle(make:String)
  case class Bike(override val make: String) extends Vehicle(make:String)
  case class EBike(override val make: String) extends Vehicle(make: String)
  object VehicleReport{
//    def printVehicle(list: List[Vehicle]): Unit = {
//      for(item <- list){
//        item match {
//          case item:Car => println(s"A car whose make is ${item.make}")
//          case item:Bike => println(s"A bike whose make is ${item.make}")
//        }
//      }
//    }
    // Upper type bound example
    def printVehicle1[V <: Vehicle](list: List[V]) = {
      for(item <- list) println(s"A vehicle whose make is ${item.make}")
    }
  }
  val l:List[Vehicle] = List(Car("Bmw 3 series"), Car("Honda CRV"), Bike("Royal Enfield"), EBike("Foo"))
  VehicleReport.printVehicle1(l)
}