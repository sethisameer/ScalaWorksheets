object VehicleInventorySystem{
  case class Vehicle(val name: String)
  trait VehicleInventory{
    def create(vehicleType: Vehicle): Unit
    def read(vehicleType: Vehicle): Unit
    def update(vehicleType: Vehicle): Unit
    def delete(vehicleType: Vehicle): Unit
  }

  class VehicleInventorySystem extends VehicleInventory {
    override def create(vehicleType: Vehicle): Unit = {
      println(s"Create vehicle = $vehicleType ")
    }
    override def read(vehicleType: Vehicle): Unit = {
      println(s"Read $vehicleType")
    }
    override def update(vehicleType: Vehicle): Unit = {
      println(s"Updated $vehicleType")
    }
    override def delete(vehicleType: Vehicle): Unit = {
      println(s"Deleted $vehicleType")
    }
  }

  val car = new VehicleInventorySystem()
  car.create(Vehicle("Lexus LX 470"))
}