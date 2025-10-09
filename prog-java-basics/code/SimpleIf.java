void main() {
    var age = Integer.parseInt(IO.readln("Wie alt sind Sie?"));
    boolean adult = false;

    if (age >= 18) { // if-Anweisung
        adult = true;
    }

    IO.println("adult=" + adult);
}