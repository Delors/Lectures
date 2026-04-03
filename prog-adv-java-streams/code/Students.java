// TO BE RUN IN THE JSHELL
//
//
static <T, K> Gatherer<T, ?, T> perGroup(
        Function<T, K> gruppierung, Comparator<T> vergleich) {

    return Gatherer.ofSequential(
        HashMap<K, T>::new,
        (map, element, downstream) -> {
            map.merge(gruppierung.apply(element), element,
                BinaryOperator.minBy(vergleich));
            return true;
        },
        (map, downstream) -> map.values().forEach(downstream::push)
    );
}


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

import static java.util.Comparator.comparingDouble;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;

List<Student> empfehlungen = alleStudierenden.stream()
    .filter(not(Student::hatStipendium))
    .gather(perGroup(Student::studiengang, comparingDouble(Student::schnitt)))
    .sorted(comparingDouble(Student::schnitt))
    .toList();

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
List<Student> empfehlungen = new ArrayList<>();
for (var eintrag : nachStudiengang.entrySet()) {
    Student bester = null;
    for (Student s : eintrag.getValue()) {
        if (bester == null || s.schnitt() < bester.schnitt()) {
            bester = s;
        }
    }
    if (bester != null) {
        empfehlungen.add(bester);
    }
}

// Schritt 4: Sortieren
empfehlungen.sort(Comparator.comparingDouble(Student::getSchnitt));
