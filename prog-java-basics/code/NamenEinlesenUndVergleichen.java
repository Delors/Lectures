void main() {
    var v1 = Integer.parseInt(IO.readln("Bitte geben Sie die erste Zahl ein: "));
    var s2 = IO.readln("Bitte geben Sie die zweite Zahl ein: ");
    var v2 = Integer.parseInt(s2);
    var result = v1 == v2;
    IO.println("Die Zahlen sind gleich: " + result);
}