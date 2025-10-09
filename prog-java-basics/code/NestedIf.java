void main() {
    var age = Integer.parseInt(IO.readln("Wie alt sind Sie?"));
    var adult = false;
    char status = 'c';

    if (age >= 18) {
        adult = true;
        status = 'b';
        if (age >= 30 && IO.readln("Geschlecht (m/w/d)?").charAt(0) == 'm')
            status = 'a';
    }
    IO.println("adult=" + adult+ ", status=" + status);
}
