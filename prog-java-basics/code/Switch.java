void main() {
    var month = Integer.parseInt(readln("Welchen Monat haben wir(1-12)? "));
    int days = 31;
    switch (month) { // vor Java 14!
        case 2:
            if (readln("Schaltjahr (j/n)? ").charAt(0) == 'j') days = 29;
            else days = 28;
            break;
        case 4:
        case 6:
        case 9:
        case 11: // <= possible, but unusual formatting
            days = 30;
            break;
    }
    println("Anzahl der Tage im Monat " + days);
}
