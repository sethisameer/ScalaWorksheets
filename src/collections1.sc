object collections1 {
  def seqOperation(n: Seq[Int]): String = n match {
    case Seq() => s"Sequence is empty"
    case Seq(first) => s"First $first"
    case Seq(first, second) => s"First two: $first, $second"
    case _ => "No match"
  }

  def f(ints: Seq[Int]): String = ints match {
    case Seq() =>
      "The Seq is empty !"
    case Seq(first) =>
      s"The seq has exactly one element : $first"
    case Seq(first, second) =>
      s"The seq has exactly two elements : $first, $second"
    case _ =>
      "The seq has more than two elements"
  }
  f(Seq(1, 3))
  seqOperation(Seq(1, 3))
}