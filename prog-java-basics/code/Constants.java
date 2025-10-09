
void main() {
    // Deklaration und Initialisierung inkl. Datentyp (Standarfall)
    final String NAME = "Asta Mueller"; 

    // vereinfachte Deklaration mittels var und Initialisierung (seit Java 10)
    // (Datentyp wird automatisch erkannt)
    final var WOHNORT = "Schönau";
    final var GESCHLECHT = 'd';

    IO.println(NAME + "(" + GESCHLECHT + "), " + " Jahre, aus " + WOHNORT);

    // name = "Berta"; // error: cannot assign a value to final variable name
}
