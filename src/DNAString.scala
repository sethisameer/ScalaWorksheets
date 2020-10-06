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
        val arr: Seq[Int] = group.map(ch => ch._2.size)
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

  def reverseCompliment(strSequence:String) = {
      val v = for(ch <- strSequence) yield ch match {
        case 'A' => 'T'
        case 'T' => 'A'
        case 'C' => 'G'
        case 'G' => 'C'
        case _ => "Unknown character"
      }
      v.reverse.mkString("")
  }
  println(reverseCompliment("AAAACCCGGT"))
}
