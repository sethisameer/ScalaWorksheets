package spark
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import org.apache.spark.storage.StorageLevel

object SparkBasicExample extends App {
  val conf = new SparkConf().setMaster("local").setAppName("My_App")
  val sc = new SparkContext(conf)

  val file = sc.textFile("/Users/same7841/Downloads/sampleFile.txt")
  val smallSample = file.take(25)
  val linesWithSherlockMention = smallSample.filter(lines => lines.contains("Sherlock"))
  val words = file.flatMap(word => word.split(" "))
//  val wordsCountInFile = words.map(words => (words, 1)).reduceByKey {
//    case (x, y) => x + y
//  }
//  wordsCountInFile.saveAsTextFile("/Users/same7841/Downloads/sampleOutput1")
//  wordsCountInFile.foreach(println)
//  println(wordsCountInFile)
  val sherlock = file.filter(_.contains("Sherlock"))
  println(sherlock.persist(StorageLevel.DISK_ONLY))
  println(sherlock.first())
//  println(sherlock.take(30).map(_ => ("sherlock", _)))
  println(sherlock.count())
  println(sherlock.collect().mkString(","))

  val smallSamplePairs = smallSample.map(word => (word.split(" ")(0), word))
  val fileMap = file.map(word => (word.split(" ")(0), 0))
  val reduceBy = fileMap.reduceByKey((x, y) => x + y)
//  reduceBy.foreach(println)

  val groupBy = fileMap.groupByKey()
//  groupBy.foreach(println)

  val linesWithLessThan10Chars = fileMap.filter{ case (key, value) => key.length < 10 }
  linesWithLessThan10Chars.foreach(println)

  /*
    * Join, left/right outer join
   */
  case class Store(soreName: String)
  val storeAddress = List(
    (Store("Ritual"), "1026 Valencia St"), (Store("Philz"), "748 Van Ness Ave"), (Store("Philz"), "3101 24th St"), (Store("Starbucks"), "Seattle"))

  val storeRating = List((Store("Ritual"), 4.9), (Store("Philz"), 4.8))

  val parallelStoreAddress = sc.parallelize(storeAddress)
  val parallelStoreRating = sc.parallelize(storeRating)
  // join
  val joinStoreAddressWithRating = parallelStoreAddress.join(parallelStoreRating)

  // Left outer join
  val leftOuterJoinStoreAddressWithRating = parallelStoreAddress.leftOuterJoin(parallelStoreRating)
  // Right outer join
  val rightOuterJoinStoreAddressWithRating = parallelStoreAddress.rightOuterJoin(parallelStoreRating)

  joinStoreAddressWithRating.foreach(println)
  leftOuterJoinStoreAddressWithRating.foreach(println)
  rightOuterJoinStoreAddressWithRating.foreach(println)
  println(file.partitioner)


  val timing = new StringBuffer
  def timed[T](label: String, code: => T): T = {
    val start = System.currentTimeMillis()
    val result = code
    val stop = System.currentTimeMillis()
    timing.append(s"Processing $label took ${stop - start} ms.\n")
    result
  }
  //
  case class AirTicket(cutomerId: String, location: String, price: Double)
  val ticketsPurchase = List(AirTicket("C007", "LAX", 230.10), AirTicket("C423", "JFK", 414.00), AirTicket("C007", "DBX", 450.30), AirTicket("C425", "LON", 3124.20), AirTicket("C423", "HLL", 242.30), AirTicket("C474", "DEL", 424.30))
  val ticketsRDD = sc.parallelize(ticketsPurchase)
  val pairCutomerIdAndPrice = ticketsRDD.map(tix => (tix.cutomerId, tix.price))

  // groupByKey example will result in excessive shuffling in this case
  val totalPurchaseByEachCustomer = pairCutomerIdAndPrice.groupByKey().collect()
//  totalPurchaseByEachCustomer.foreach(println)
  timed(s"groupByKey took ", totalPurchaseByEachCustomer.foreach(println))
  println(timing)

  // reduceByKey example will result in lesser shuffling of data between partitions
  val totalPurchaseByEachCustomer1 = pairCutomerIdAndPrice.reduceByKey((_, price) => price).collect()
  timed(s"reduceByKey took ", totalPurchaseByEachCustomer1.foreach(println))
  println(timing)

  Thread.sleep(86400000)

}

