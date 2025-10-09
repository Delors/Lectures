void main() {
    final var month = Integer.parseInt(IO.readln("Welchen Monat haben wir (1-12)? "));
    final var day = Integer.parseInt(
        IO.println("Welchen Tag des Monats haben wir (1-28/29/30/31)? ")
        );
    final var dayOnJanuary1st = Integer.parseInt(
        IO.println("Welcher Wochentag war der 1.1. (1=Montag, ..., 7=Sonntag)? ")
        )-1;
    final var leapYear = IO.readln("Ist das Jahr ein Schaltjahr (j/n)? ").charAt(0) == 'j';

    var dayInYear = day; // Wir summieren hier die Tage des Jahres auf
    switch (month - 1) {
        case 12:
        case 11:
            dayInYear += 30;
        case 10:
            dayInYear += 31;
        case 9:
            dayInYear += 30;
        case 8:
            dayInYear += 31;
        case 7:
            dayInYear += 31;
        case 6:
            dayInYear += 30;
        case 5:
            dayInYear += 31;
        case 4:
            dayInYear += 30;
        case 3:
            dayInYear += 31;
        case 2:
            dayInYear += 28;
            if (leapYear)
                dayInYear++;
        case 1:
            dayInYear += 31;
            break;
        default:
            IO.println("Fehler: Monat nicht bekannt");
            return;
    }
    IO.println("Tag im Jahr: " + dayInYear);

    var dayInWeek = (dayOnJanuary1st + dayInYear) % 7;
    IO.println("Tag in der Woche: " + dayInWeek);

    final String[] dayInWeekName = {
            "Sonntag",    
            "Montag",
            "Dienstag",
            "Mittwoch",
            "Donnerstag",
            "Freitag",
            "Samstag"
    };
    IO.println("Der " + day + "." + month + ". ist ein " + dayInWeekName[dayInWeek]);
}