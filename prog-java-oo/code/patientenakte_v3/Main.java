import static java.io.Console.*;
import static java.text.DateFormat.getDateInstance;

void main() throws Exception {
    var arzt = new Arzt();
    arzt.addPatient(
            new Patient("12/12/2024", "Max Mustermann", 180, 80d));

    arzt.addPatient(new Patient("12/12/2024", "Erika Musterfrau", 160, 60));
    arzt.addPatient(new Patient("12/12/2024", "Anna Musterfrau", 130, 50));

    IO.println("Durchschnittsgröße: " + arzt.berechneDurschnittsgroesse());
}