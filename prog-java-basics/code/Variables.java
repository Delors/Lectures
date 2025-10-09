
void main() {
    // Deklaration (Datentyp muss konkret angegeben werden)
    int alter; 
    // Deklaration und Initialisierung inkl. Datentyp (Standarfall)
    String name = "Asta Mueller"; 

    // vereinfachte Deklaration mittels var und Initialisierung (seit Java 10)
    // (Datentyp wird automatisch erkannt)
    var geburtsOrt = "Berlin";
    var wohnort = "Schönau";
    var geschlecht = 'd';

    alter = 25; // späte Initialisierung
    IO.println(name + "(" + geschlecht + "), " + alter + " Jahre, aus " + wohnort);
}
