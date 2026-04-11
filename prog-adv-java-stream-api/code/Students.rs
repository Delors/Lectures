use itertools::Itertools;

#[derive(Debug)]
struct Student {
    hat_stipendium: bool,
    studiengang: String,
    schnitt: f64,
}

impl Student {
    fn new(hat_stipendium: bool, studiengang: &str, schnitt: f64) -> Self {
        Student {
            hat_stipendium,
            studiengang: studiengang.to_string(),
            schnitt,
        }
    }
}

fn main() {
    let alle_studierenden = vec![
        Student::new(false, "Informatik", 1.3),
        Student::new(false, "Informatik", 2.1),
        Student::new(true, "Informatik", 1.0),
        Student::new(false, "Informatik", 2.7),
        Student::new(false, "Informatik", 1.9),
        Student::new(false, "BWL", 1.7),
        Student::new(false, "BWL", 2.4),
        Student::new(false, "BWL", 1.1),
        Student::new(true, "BWL", 1.2),
        Student::new(false, "BWL", 3.0),
        Student::new(false, "Maschinenbau", 1.5),
        Student::new(false, "Maschinenbau", 2.3),
        Student::new(false, "Maschinenbau", 1.8),
        Student::new(true, "Maschinenbau", 1.1),
        Student::new(false, "Maschinenbau", 2.9),
        Student::new(false, "Medizin", 1.0),
        Student::new(false, "Medizin", 1.4),
        Student::new(false, "Medizin", 2.2),
        Student::new(true, "Medizin", 1.3),
        Student::new(false, "Medizin", 1.8),
        Student::new(false, "Jura", 1.6),
        Student::new(false, "Jura", 2.5),
        Student::new(false, "Jura", 1.2),
        Student::new(true, "Jura", 1.0),
        Student::new(false, "Jura", 3.1),
        Student::new(false, "Physik", 1.1),
        Student::new(false, "Physik", 2.0),
        Student::new(false, "Physik", 1.7),
        Student::new(true, "Physik", 1.2),
        Student::new(false, "Physik", 2.8),
    ];

    let empfehlungen: Vec<&Student> = alle_studierenden
        .iter()
        .filter(|s| !s.hat_stipendium)
        .into_group_map_by(|s| &s.studiengang)
        .into_values()
        .map(|gruppe| {
            gruppe
                .into_iter()
                .min_by(|a, b| a.schnitt.total_cmp(&b.schnitt))
                .unwrap()
        })
        .sorted_by(|a, b| a.schnitt.total_cmp(&b.schnitt))
        .collect();

    println!("Empfehlungsliste:");
    for s in &empfehlungen {
        println!("  {}: {}", s.studiengang, s.schnitt);
    }
}
