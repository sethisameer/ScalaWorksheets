object CarInventory{
 // Car inventory sorting, implicity definition and implicity class examples
 final case class Car(name: String, price: Double)
  type CarStock = Tuple2[Car, Int]

  val vw = new CarStock(Car("VW Passat", 23400), 20)
  val honda = new CarStock(Car("Honda Civic", 19999), 152)
  val mazda = new CarStock(Car("Mazda 3", 18999), 12)
  val toyota = new CarStock(Car("Toyota 4Runner", 38500), 5)

  val inventoryList: List[CarStock] = List(vw, honda, mazda, toyota)
  // Sort invetory based on higheset price first
 val sortedByHigherPrice = inventoryList.sortBy(_._1.price).reverse
 val sortedByLowerInStock = inventoryList.sortBy(_._2).reverse
 println("Inventory of cars matching price higher ")
 for(inventory <- sortedByHigherPrice )  yield println(s"${inventory._1.name} = ${inventory._2}")

 println("Inventory of cars matching most available in stock")
 for(inventory <- sortedByLowerInStock )  yield println(s"${inventory._1.name} = ${inventory._2}")
 // uncomment for implicit def example
// class CarUUID(car:Car){
//   def uuid:String = s"car uuid = ${car.name} - ${car.hashCode}"
// }
// object CarExtensionImplicitFuncitonExample{
//  implicit def uuid(car: Car): CarUUID = new CarUUID(car)
// }

 // implicity class
 object CarExtensionImplicitClassExample{
  implicit class CarUUID(car: Car){
   def uuid:String = s"car uuid = ${car.name} - ${car.hashCode}"
   def moreInfo: String = s"some more info...."
  }

 }
// import CarExtensionImplicitFuncitonExample._
 import CarExtensionImplicitClassExample._
 for(inventory <- sortedByLowerInStock )  yield println(s"${inventory._1.uuid} with ${inventory._1.moreInfo}")
}