// Achtung: --enable-preview mit Java 25 Benötigt!
// Naive Implementation zur Berechnung der Fibonacci-Zahlen
void main() {
    var n = Integer.parseInt(IO.readln("Berechnung der n-ten Fibonacci-Zahl. Bitte n eingeben: "));
    switch (n) {
        case 0 -> { IO.println("0"); }
        case 1 -> { IO.println("1"); }
        case _ when n > 1 -> {
            var a = 0l;
            var b = 1l;
            long c = a + b;
            for (int i = 2; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }   
            IO.println(c);
        }
        default -> {
            IO.println("die Zahl muss größer gleich 0 sein");
        }
    }
}
