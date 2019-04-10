package monkeypatching

object PersonExtension {

  def incAge(person: Person): Person = {
    person.copy(age = person.age + 1)
  }
}
