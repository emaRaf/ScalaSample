package monkeypatching



object PersonExtensions {
    implicit class PersonExtensionsImplicit(person: Person)
    {
      def incrementAgeImplicit = {
        println("running PersonExtensionsImplicit::incrementAgeImplicit from object PersonExtensions")
        person.copy(age = person.age + 1)
      }
    }
}
