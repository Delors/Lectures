void main() {
    final var month = Integer.parseInt(
            IO.println("Welchen Monat haben wir (1-12)? "));
    final var dayInMonth = Integer.parseInt(
            IO.println("Welchen Tag des Monats haben wir (1-28/29/30/31)? "));
    final var dayOnJanuary1st = Integer.parseInt(
            IO.println("Welcher Wochentag war der 1.1. (1=Montag, ..., 7=Sonntag)? ")) - 1;

    var dayInYear = dayInMonth; // Wir summieren hier die Tage des Jahres auf
    switch (month - 1) {
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
            if (IO.readln("Ist das Jahr ein Schaltjahr (j/n)? ").charAt(0) == 'j')
                dayInYear++;
        case 1:
            dayInYear += 31;
            break;
        case 0:
            break;
        default:
            IO.println("Fehler: Monat nicht bekannt");
            return;
    }
    IO.println("Tag im Jahr: " + dayInYear);

    var dayInWeek = (dayOnJanuary1st + dayInYear) % 7;
    IO.println("Tag in der Woche: " + dayInWeek);

    var dayInWeekName = switch (dayInWeek) {
        case 0 -> "Sonntag";
        case 1 -> "Montag";
        case 2 -> "Dienstag";
        case 3 -> "Mittwoch";
        case 4 -> "Donnerstag";
        case 5 -> "Freitag";
        case 6 -> "Samstag";
        default -> "Fehler: Tag nicht bekannt";
    };
    IO.println("Der " + dayInMonth + "." + month + ". ist ein " + dayInWeekName);
}