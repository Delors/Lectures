// TO BE RUN IN THE JSHELL
//
//
record Student(boolean hatStipendium, String studiengang, double schnitt){};

List<Student> alleStudierenden = List.of(
    new Student(false, "Informatik",      1.3),
    new Student(false, "Informatik",      2.1),
    new Student(true,  "Informatik",      1.0),
    new Student(false, "Informatik",      2.7),
    new Student(false, "Informatik",      1.9),
    new Student(false, "BWL",             1.7),
    new Student(false, "BWL",             2.4),
    new Student(false, "BWL",             1.1),
    new Student(true,  "BWL",             1.2),
    new Student(false, "BWL",             3.0),
    new Student(false, "Maschinenbau",    1.5),
    new Student(false, "Maschinenbau",    2.3),
    new Student(false, "Maschinenbau",    1.8),
    new Student(true,  "Maschinenbau",    1.1),
    new Student(false, "Maschinenbau",    2.9),
    new Student(false, "Medizin",         1.0),
    new Student(false, "Medizin",         1.4),
    new Student(false, "Medizin",         2.2),
    new Student(true,  "Medizin",         1.3),
    new Student(false, "Medizin",         1.8),
    new Student(false, "Jura",            1.6),
    new Student(false, "Jura",            2.5),
    new Student(false, "Jura",            1.2),
    new Student(true,  "Jura",            1.0),
    new Student(false, "Jura",            3.1),
    new Student(false, "Physik",          1.1),
    new Student(false, "Physik",          2.0),
    new Student(false, "Physik",          1.7),
    new Student(true,  "Physik",          1.2),
    new Student(false, "Physik",          2.8)
);


////////// IMPERATIVER CODE

// Schritt 1: Studierende ohne Stipendium sammeln
List<Student> ohneStipendium = new ArrayList<>();
for (Student s : alleStudierenden) {
    if (!s.hatStipendium()) {
        ohneStipendium.add(s);
    }
}

// Schritt 2: Nach Studiengang gruppieren
Map<String, List<Student>> nachStudiengang = new HashMap<>();
for (Student s : ohneStipendium) {
    nachStudiengang
        .computeIfAbsent(s.studiengang(), k -> new ArrayList<>())
        .add(s);
}

// Schritt 3: Pro Gruppe den Besten finden
List<Student> empfehlungenI = new ArrayList<>();
for (var eintrag : nachStudiengang.entrySet()) {
    Student bester = null;
    for (Student s : eintrag.getValue()) {
        if (bester == null || s.schnitt() < bester.schnitt()) {
            bester = s;
        }
    }
    if (bester != null) {
        empfehlungenI.add(bester);
    }
}

// Schritt 4: Sortieren
empfehlungenI.sort(Comparator.comparingDouble(Student::schnitt));

// Ausgabe
empfehlungenI.forEach(System.out::println);


////////// STREAM-BASIERTER CODE

// JAVA 8-24
//
import static java.util.Comparator.comparingDouble;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;

List<Student> empfehlungenS = alleStudierenden.stream()
    .filter(not(Student::hatStipendium))
    .collect(groupingBy(Student::studiengang,
                        minBy(comparingDouble(Student::schnitt))))
    .values().stream()
    .flatMap(Optional::stream)
    .sorted(comparingDouble(Student::schnitt))
    .toList();

empfehlungenS.forEach(IO::println);

// JAVA 25+

static <T, K> Gatherer<T, ?, T> reducePerGroup(
        Function<T, K> grouping, BinaryOperator<T> reducer) {
    return Gatherer.ofSequential(
        HashMap<K, T>::new,
        (map, element, downstream) -> {
            map.merge(grouping.apply(element), element, reducer);
            return true;
        },
        (map, downstream) -> map.values().forEach(downstream::push)
    );
}

List<Student> empfehlungenG = alleStudierenden.stream()
    .filter(not(Student::hatStipendium))
    .gather(reducePerGroup(Student::studiengang, BinaryOperator.minBy(comparingDouble(Student::schnitt))))
    .sorted(comparingDouble(Student::schnitt))
    .toList();

empfehlungenG.forEach(IO::println);
