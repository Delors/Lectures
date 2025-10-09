void main() {
    var month = Integer.parseInt(IO.readln("Welchen Monat haben wir(1-12)? "));
    // seit Java 14:
    int days =
        switch (month) { // Switch-Ausdruck
            case 2:
                yield IO.readln("Schaltjahr (j/n)? ").charAt(0) == 'j' ? 29 : 28;
            case 4, 6, 9, 11:
                yield 30;
            default:
                yield 31;
        };
    IO.println("Anzahl der Tage im Monat " + days);
}
