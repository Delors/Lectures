void main() {
    var month = Integer.parseInt(IO.readln("Welchen Monat haben wir(1-12)? "));
    int days = 31;
    switch (month) { // seit Java 14
        case 2 -> { // Block oder Ausdruck!
            if (IO.readln("Schaltjahr (j/n)? ").charAt(0) == 'j') days = 29;
            else days = 28;
        }
        case 4, 6, 9, 11 -> days = 30;
    }
    IO.println("Anzahl der Tage im Monat " + days);
}
