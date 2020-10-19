object CollectionsExample extends App{
  case class Film(name: String, yearOfRelease: Int, imdbRating: Double)

  case class Director(firstName: String, lastName: String, yearOfBirth: Int, films: Seq[Film])

  val memento = new Film("Memento", 2000, 8.5)
  val darkKnight = new Film("Dark Knight", 2008, 9.0)
  val inception = new Film("Inception", 2010, 8.8)

  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = new Film("Unforgiven", 1992, 8.3)
  val granTorino = new Film("Gran Torino", 2008, 8.2)
  val invictus = new Film("Invictus", 2009, 7.4)

  val predator = new Film("Predator", 1987, 7.9)
  val dieHard = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))

  val someGuy = new Director("Just", "Some Guy", 1990,
    Seq())

  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  def directorWithMaxFilms(numberOfFilms: Int, year: Int, ascending:Boolean = true ) = {
    val byNumberOfFilms = directors.filter(director => director.films.length >= numberOfFilms)
    val byAge = directors.filter(director => director.yearOfBirth <= year)
    byAge.filter(byNumberOfFilms.contains).sorted[Director](Ordering.fromLessThan((A, B) => if(ascending) A.yearOfBirth < B.yearOfBirth else A.yearOfBirth > B.yearOfBirth ))
//    director.groupBy(_.firstName)
  }
  val moviesDriectedBy = (directorFirstName:String) => directors.filter(director => director.firstName == directorFirstName)

  val movieNamesOfDirector= (directorFirstName: String) => moviesDriectedBy(directorFirstName).flatMap(f => f.films.map(film => film.name))

  val moviesByAllDirectors = directors.flatMap(f => f.films.map(film => film.name))

  val moviesByAllDirectors1 = directors.flatten(f => movieNamesOfDirector(f.firstName))

  val moviesDirectedByMcTiernan = moviesDriectedBy("John").map(f => f.films.sortBy(film => film.yearOfRelease)(Ordering.fromLessThan(_ > _)))
  // Sort movies of all directors based on imdb rating
  val sortAllMoviesByRating = directors.flatMap(director => moviesDriectedBy(director.firstName)).map(f => f.films.sortBy(film => film.imdbRating)(Ordering.fromLessThan(_ > _)))

//  val showingTonight = directors.foreach(director => director.films.foreach(film => println(s"Showing tonight ${film.name} by ${director.firstName} ${director.lastName}")))
  /// Using For Comprehension

  val moviesDriectedByUsingFor = (directorFirstName:String) => for {director <- directors.filter(d => d.firstName == directorFirstName)} yield director
  val movieNamesOfDirectorUsingFor= (directorFirstName: String) => for {
    director <- moviesDriectedByUsingFor(("Christopher"))
    film <- director.films
  } yield film.name

  val moviesDirectedByMcTiernanUsingFor = for{
    director <- moviesDriectedByUsingFor("Christopher")
    film <- director.films.sortBy(film => film.yearOfRelease)(Ordering.fromLessThan(_ > _))
    } yield film

//  println(directorWithMaxFilms(3, 1951))
  println(movieNamesOfDirector("Christopher"))
//  println(moviesByAllDirectors)
//  println(moviesByAllDirectors1)
//  println(moviesDirectedByMcTiernan)
//  println(sortAllMoviesByRating)
//  println(showingTonight)
//
  println(moviesDriectedBy(("Christopher")))
  println(moviesDriectedByUsingFor(("Christopher")))
  println(movieNamesOfDirectorUsingFor("Christopher"))
  println(moviesDirectedByMcTiernanUsingFor)

  val seq1 = Seq(44, 54, 2)
  val seq2 = Seq(4, 5, 3)
  val pairWithAnotherSequence = seq1.zip(seq2)

  println(pairWithAnotherSequence)
  val sumOfSequences = for { (a, b) <- seq1.zip(seq2) } yield a + b
  println(sumOfSequences)

  val intermediateOperationInForCompreshension = for{ x <- seq1
                                                   product = x * 2
                                                   y <- seq2
                                                  } yield product * y
  println(intermediateOperationInForCompreshension)
}