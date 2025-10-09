
/**
 * Returns whether a year is a leap year.
 * 
 * @param year The year to check. Year &gt;= 0.
 * @return true if the year is a leap year, false otherwise.
 */
boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}

/**
 * Returns the number of days in a year.    
 * 
 * @param year The year for which you want to know the number of days. Year &gt;= 0.
 * @return The number of days in the year. 365 or 366.  
 */
int numberOfDays(int year) {
    return isLeapYear(year) ? 366 : 365;
}

/**
 * Returns the number of days since the start of a year for a given date. (Including the given date.)    
 * 
 * @param year  The year of the date. Year &gt;= 0.
 * @param month The month of the date. 1 &lt;= month &lt;= 12.
 * @param day   The day of the date. 1 &lt;= day &lt;= 28/29/30/31.
 * @return The day in the year for the given date. 1 &lt;= dayInYear &lt;= 366.
 */
int dayInYear(int year, int month, int day) {
    var dayInYear = day;
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
            if (isLeapYear(year))
                dayInYear++;
        case 1:
            dayInYear += 31;
        case 0:
            break;
        default:
            IO.println("Fehler: Monat nicht bekannt");
            System.exit(-1);
            return -1; // effectively unreachable code
    }
    return dayInYear;
}

void main() {
    assert dayInYear(2020, 1, 1) == 1;
    assert dayInYear(2020, 2, 29) == 60;
    assert dayInYear(2019, 3, 1) == 60: dayInYear(2019, 3, 1);
    assert isLeapYear(0);
    assert isLeapYear(2000);
    assert !isLeapYear(1900);
    assert !isLeapYear(1903);


    final var birthYear = Integer.parseInt(
            IO.println("In welchem Jahr sind Sie geboren? "));
    final var birthMonth = Integer.parseInt(
            IO.println("In welchem Monat sind Sie geboren (1-12)? "));
    final var birtDayInMonth = Integer.parseInt(
            IO.println("An welchem Tag des Monats sind Sie geboren (1-28/29/30/31)? "));

    final var currentYear = Integer.parseInt(
            IO.println("Welches Jahr haben wir? "));
    final var currentMonth = Integer.parseInt(
            IO.println("Welchen Monat haben wir (1-12)? "));
    final var currentDayInMonth = Integer.parseInt(
            IO.println("Welchen Tag des Monats haben wir (1-28/29/30/31)? "));

    if (currentYear < birthYear) {
        IO.println("Fehler: Das aktuelle Jahr ist kleiner als das Geburtsjahr.");
        System.exit(-1);
    }
    if (currentYear == birthYear && currentMonth < birthMonth) {
        IO.println("Fehler: Der aktuelle Monat ist kleiner als der Geburtsmonat.");
        System.exit(-1);
    }
    if (currentYear == birthYear && currentMonth == birthMonth && currentDayInMonth < birtDayInMonth) {
        IO.println("Fehler: Der aktuelle Tag ist kleiner als der Geburtstag.");
        System.exit(-1);
    }

    if (currentYear == birthYear) {
        IO.println("Sie haben " + (dayInYear(currentYear, currentMonth, currentDayInMonth)
                - dayInYear(birthYear, birthMonth, birtDayInMonth)) + " Tage gelebt.");
    } else {
        var daysLived = dayInYear(currentYear, currentMonth, currentDayInMonth) +
                numberOfDays(birthYear) - dayInYear(birthYear, birthMonth, birtDayInMonth);
        for (int year = birthYear + 1; year < currentYear; year++) {
            daysLived += numberOfDays(year);
        }
        IO.println("Sie haben " + daysLived + " Tage gelebt.");
    }
}