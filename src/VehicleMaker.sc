object VehicleMaker1{
  trait Vehicle {
    def printName(): Unit
  }

  class Car(name: String) extends Vehicle {
    override def printName(): Unit = println(name)
  }

  class BmwCar(name: String) extends Car(name) {
    override def printName(): Unit = println(name)
  }

  class MazdaCar(name: String) extends Car(name) {
    override def printName(): Unit = println(name)
  }

  class Bike(name: String) extends Vehicle{
    override def printName(): Unit = println(name)
  }

  class HondaBike(name: String) extends Bike(name) {
    override def printName(): Unit = println(name)
  }

  class BmwBike(name: String) extends Bike(name) {
    override def printName(): Unit = println(name)
  }
  class VehicleMaker[V <: Vehicle](val vehicle: Option[V] = None){
    def make(): Option[V] = {
      println("Making vehicle ")
      vehicle.map(v => {
        v.printName()
        v
      })
    }
    // Implicit evidence feature
    // both A and B should be of same Brand
    def makeSimilarCars[A <: V, B <: V](vehicleA: A, vehicleB: B)(implicit ev: A =:= B): Unit = {
      vehicleA.printName()
      vehicleB.printName()
    }
    // Implicit evidence feature
    // A and B could be different brand but of trait Vehicle
    def makeBikes[A <: V, B <: V](bike1: A, bike2: B)(implicit ev: A <:< V): Unit = {
      bike1.printName()
      bike2.printName()
    }

  }

  val bmw5Car = new BmwCar("bmw car 5 series")
  val bmw3Car = new BmwCar("bmw car 3 series")
  val hondaBike = new HondaBike("honda bike firestorm")
  val bmwBike = new BmwBike("bmw bike r 2000")
  val vm = new VehicleMaker[Vehicle](Some(bmw5Car))
//    vm.make()
    vm.makeSimilarCars(bmw3Car, bmw5Car)
    vm.makeBikes(hondaBike, bmwBike)
}