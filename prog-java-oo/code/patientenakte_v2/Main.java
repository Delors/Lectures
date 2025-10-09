import static java.io.Console.*;
import static java.text.DateFormat.getDateInstance;

double berechneDurschnittsgroesse(Patient[] patienten) {
    double durchschnittsGroesse = 0;
    for (var patient : patienten) {
        durchschnittsGroesse += (double)patient.getGroesse() / patienten.length;
    }
    return durchschnittsGroesse;
}

void main() throws Exception {
    var patienten = new Patient[] {
            new Patient("12/12/2024", "Max Mustermann", 180, 80d),
            new Patient("12/12/2024", "Erika Musterfrau", 160, 60),
            new Patient("12/12/2024", "Anna Musterfrau", 130, 50)
    };

    IO.println("Durchschnittsgröße: " + berechneDurschnittsgroesse(patienten));
}