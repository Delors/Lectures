class Student {
    constructor(hatStipendium, studiengang, schnitt) {
        this.hatStipendium = hatStipendium;
        this.studiengang = studiengang;
        this.schnitt = schnitt;
    }
    toString() {
        return `Student(${this.hatStipendium}, "${this.studiengang}", ${this.schnitt})`;
    }
}

const alleStudierenden = [
    new Student(false, "Informatik", 1.3),
    new Student(false, "Informatik", 2.1),
    new Student(true, "Informatik", 1.0),
    new Student(false, "Informatik", 2.7),
    new Student(false, "Informatik", 1.9),
    new Student(false, "BWL", 1.7),
    new Student(false, "BWL", 2.4),
    new Student(false, "BWL", 1.1),
    new Student(true, "BWL", 1.2),
    new Student(false, "BWL", 3.0),
    new Student(false, "Maschinenbau", 1.5),
    new Student(false, "Maschinenbau", 2.3),
    new Student(false, "Maschinenbau", 1.8),
    new Student(true, "Maschinenbau", 1.1),
    new Student(false, "Maschinenbau", 2.9),
    new Student(false, "Medizin", 1.0),
    new Student(false, "Medizin", 1.4),
    new Student(false, "Medizin", 2.2),
    new Student(true, "Medizin", 1.3),
    new Student(false, "Medizin", 1.8),
    new Student(false, "Jura", 1.6),
    new Student(false, "Jura", 2.5),
    new Student(false, "Jura", 1.2),
    new Student(true, "Jura", 1.0),
    new Student(false, "Jura", 3.1),
    new Student(false, "Physik", 1.1),
    new Student(false, "Physik", 2.0),
    new Student(false, "Physik", 1.7),
    new Student(true, "Physik", 1.2),
    new Student(false, "Physik", 2.8),
];

const empfehlungen =
    Object.values(
        Object.groupBy(
            alleStudierenden.filter((s) => !s.hatStipendium),
            (s) => s.studiengang,
        )
    ).
    map((gruppe) => gruppe.reduce((a, b) => (a.schnitt < b.schnitt ? a : b))).
    toSorted((a, b) => a.schnitt - b.schnitt);

console.log("Empfehlungsliste:");
empfehlungen.forEach((s) => console.log(`  ${s.studiengang}: ${s.schnitt}`));
