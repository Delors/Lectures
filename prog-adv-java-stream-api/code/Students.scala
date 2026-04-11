// Ausführbar mit: scala Students.scala (Scala 3)
case class Student(
    hatStipendium: Boolean,
    studiengang: String,
    schnitt: Double
)

@main def stipendien(): Unit =

    val alleStudierenden = List(
        Student(false, "Informatik",    1.3),
        Student(false, "Informatik",    2.1),
        Student(true,  "Informatik",    1.0),
        Student(false, "Informatik",    2.7),
        Student(false, "Informatik",    1.9),
        Student(false, "BWL",           1.7),
        Student(false, "BWL",           2.4),
        Student(false, "BWL",           1.1),
        Student(true,  "BWL",           1.2),
        Student(false, "BWL",           3.0),
        Student(false, "Maschinenbau",  1.5),
        Student(false, "Maschinenbau",  2.3),
        Student(false, "Maschinenbau",  1.8),
        Student(true,  "Maschinenbau",  1.1),
        Student(false, "Maschinenbau",  2.9),
        Student(false, "Medizin",       1.0),
        Student(false, "Medizin",       1.4),
        Student(false, "Medizin",       2.2),
        Student(true,  "Medizin",       1.3),
        Student(false, "Medizin",       1.8),
        Student(false, "Jura",          1.6),
        Student(false, "Jura",          2.5),
        Student(false, "Jura",          1.2),
        Student(true,  "Jura",          1.0),
        Student(false, "Jura",          3.1),
        Student(false, "Physik",        1.1),
        Student(false, "Physik",        2.0),
        Student(false, "Physik",        1.7),
        Student(true,  "Physik",        1.2),
        Student(false, "Physik",        2.8)
    )

    val empfehlungen = alleStudierenden
        .filterNot(_.hatStipendium)
        .groupBy(_.studiengang)
        .values
        .map(_.minBy(_.schnitt))
        .toList
        .sortBy(_.schnitt)

    println("Empfehlungsliste:")
    empfehlungen.foreach(s =>
        println(s"  ${s.studiengang}: ${s.schnitt}")
    )

    val lazyEmpfehlungen = alleStudierenden.to(LazyList)
        .filterNot(_.hatStipendium)
        .groupBy(_.studiengang)
        .values
        .map(_.minBy(_.schnitt))
        .toList
        .sortBy(_.schnitt)

    println("Lazy Empfehlungsliste:")
    lazyEmpfehlungen.foreach(s =>
        println(s"  ${s.studiengang}: ${s.schnitt}")
    )
