//package com.xyzcorp;
//
//case class Team(city:String, mascot:String)
//
////Create two choices to sort by, city and mascot
//object MyPredef3 {
////  implicit val teamsSortedByCity: Ordering[Team] =
////    (x: Team, y: Team) => x.city compare y.city
////  implicit val teamsSortedByMascot: Ordering[Team] =
////    (x: Team, y: Team) => x.mascot compare y.mascot
//}
//object ImplicitExample extends App{
//  //Create some sports teams
//  val teams = List(Team("Cincinnati", "Bengals"),
//    Team("Madrid", "Real Madrid"),
//    Team("Las Vegas", "Golden Knights"),
//    Team("Houston", "Astros"),
//    Team("Cleveland", "Cavaliers"),
//    Team("Arizona", "Diamondbacks"))
//
//  //import the implicit rule we want, in this case city
//  import MyPredef3.teamsSortedByCity
//
//  //min finds the minimum, since we are sorting
//  //by city, Arizona wins.
//  println(teams.min.city)
//}
