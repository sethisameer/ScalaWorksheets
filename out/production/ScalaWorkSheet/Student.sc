   abstract class Student(name: String, age: Int, favoriteFood: Option[String] = None) {
      def printName(): Unit

      def studentId(): Int = {
        val id = name.hashCode
        id
      }
    }

    class PrimaryStudent(name: String, age: Int, favoriteFood: Option[String]) extends Student(name, age, favoriteFood) {
      override def printName(): Unit = favoriteFood match {
        case Some(favoriteFood) => println(s"${name} who is a primary student and is ${age} years old and her favorite food is ${favoriteFood}")
        case None => println(s"${name} who is a primary student and is ${age} years old.")
      }
      override def studentId(): Int = {
        val id = name.hashCode + PrimaryStudent.hashCode
        id
      }

    }

    object PrimaryStudent{
      def apply(name: String, age: Int, favoriteFood: Option[String]):PrimaryStudent = new PrimaryStudent(name, age, favoriteFood)
    }

    class SecondaryStudent(name: String, age: Int, favoriteFood: Option[String]) extends Student(name, age, favoriteFood) {
      override def printName(): Unit = favoriteFood match {
        case Some(favoriteFood) => println(s"${name} who is a secondary student and is ${age} years old and her favorite food is ${favoriteFood}")
        case None => println(s"${name} who is a secondary student and is ${age} years old.")
      }
      override def studentId(): Int = {
        val id = name.hashCode + SecondaryStudent.hashCode
        id
      }
    }

    object SecondaryStudent{
      def apply(name: String, age: Int, favoriteFood: Option[String]):SecondaryStudent = new SecondaryStudent(name, age, favoriteFood)
    }

    val allStudents:List[Student] = List(
      PrimaryStudent("Sean", 9, Some("Pasta")),
      PrimaryStudent("Hall", 10, Some("Fries")),
      SecondaryStudent("Rebecca", 14, None),
      SecondaryStudent("Drew", 16, Some("Chicken Wings"))
    )
    def listStudents(students: List[Student]): Unit = {
      for( student <- students) println(s"${student.printName} id is ${student.studentId}")
    }
    listStudents(allStudents)

