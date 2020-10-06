object simpleStudent {

  final case class Student(name: String, age: Int)

  val students: Vector[Student] = Vector(
    Student("Jill", 11),
    Student("Sean", 18),
    Student("Roger", 8),
    Student("Tammy", 4),
    Student("Sunday", 16)
  )

  def findHighSchoolStudents(age: Int, students: Vector[Student], index: Int): Boolean = {
    if (index == students.length) false
    else if (students(index).age >= age) true
    else findHighSchoolStudents(age, students, index + 1)
  }

  val hasHighSchoolStuent = findHighSchoolStudents(14, students, 0)
  println(s"Is there any high school student = ${hasHighSchoolStuent}")
}