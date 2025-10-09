void main() {
    IO.println("Anzahl der Tage in einem Monat");

    var month = Integer.parseInt(IO.readln("Welchen Monat haben wir(1-12)? "));

    int days = 31;
    if (month == 2 && IO.readln("Schaltjahr (j/n)? ").charAt(0) == 'j') days = 29;
    else if (month == 2) days = 28; // "nur" umformatiert
    else if (month == 4) days = 30;
    // else ...
    IO.println("days=" + days);
}
