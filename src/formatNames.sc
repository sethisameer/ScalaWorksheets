object formatNames{
  def toUpper(str: String): String = {
    s"Hello ${str.toUpperCase}"
  }

  def toLower(str: String): String = {
    s"Hello ${str.toLowerCase}"
  }

  def formatNames(str: String, group: String => String): String = {
    group(str)
  }
  formatNames("john", toLower)
  formatNames("john", toUpper)

}