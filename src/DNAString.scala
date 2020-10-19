object DNAString extends App {
  // Puzzle ref: http://rosalind.info/problems/dna/
  //Given: A DNA string s of length at most 1000 nt.
  //Return: Four integers (separated by spaces) counting the respective number of times that the symbols 'A', 'C', 'G', and 'T' occur in s.
  // Sample Outpu: 20 12 17 21

  def countingDNANucleotides(strSequence:String): String = {
    strSequence match {
      case strSequence if(strSequence.isEmpty) => "No Sequence found"
      case strSequence if(strSequence.length > 1000) => "Invalid sequence range"
      case strSequence =>  val group = strSequence.groupBy(char => char).toSeq.sortBy(_._1)
        val arr: Seq[Int] = group.map(ch => ch._2.length)
        arr.mkString("  ")
      case _ => s"Unknow error"
    }

    //    println(group.toMap.mapValues(x => x.size))
  }
  val strExample = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"
  println(countingDNANucleotides(strExample))

  //  An RNA string is a string formed from the alphabet containing 'A', 'C', 'G', and 'U'.
  //  Given a DNA string t corresponding to a coding strand, its transcribed RNA string u is formed by replacing all occurrences of 'T' in t with 'U' in u.
  //  Given: A DNA string t having length at most 1000 nt.
  //  Return: The transcribed RNA string of t.

  def tranformString(strSequence:String, replaceWith: Char):String = {

    strSequence match {
      case strSequence if(strSequence.isEmpty) => "No Sequence found"
      case strSequence if(strSequence.length > 1000) => "Invalid sequence range"
      case strSequence => strSequence.replace('T', 'U')
      case _ => s"Unknow error"
    }
  }
  println(tranformString("GATGGAACTTGACTACGTAAATT", 'U'))

  /*
 Problem:
 In DNA strings, symbols 'A' and 'T' are complements of each other, as are 'C' and 'G'.
 The reverse complement of a DNA string s is the string sc formed by reversing the symbols of s, then taking the complement of each symbol (e.g., the reverse complement of "GTCA" is "TGAC").
 Given: A DNA string s of length at most 1000 bp.

 Return: The reverse complement sc of s.

 Example: Sample Dataset: AAAACCCGGT
 Output: ACCGGGTTTT
 */

  def complementingDNAString(strSequence: String): String = {
    strSequence match {
      case strSequence: String if (strSequence.isEmpty) =>
        "No Sequence found" // Log
      case strSequence: String if (strSequence.length > 1000) =>
        "Invalid sequence range" // Log
      case strSequence: String => reverseCompliment(strSequence)
      case _                   => s"Unknow error" // Log
    }
  }

  def reverseCompliment(strSequence: String): String = {
    val v = for (ch <- strSequence) yield ch match {
      case 'A' => 'T'
      case 'T' => 'A'
      case 'C' => 'G'
      case 'G' => 'C'
      case _   => "Unknown character"
    }
    v.reverse.mkString("")
  }

  val dnaSequence: String = "AAAACCCGGT"
  val output = complementingDNAString(dnaSequence)

  println(s"Reverse compliment for DNA Sequence ${dnaSequence} is : $output")
}
