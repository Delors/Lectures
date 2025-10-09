void main() {
    var name = IO.readln("Wie ist Dein Name? ");
    String nameAnalysis = switch (name) { 
        // Der erste Vergleich, der zutrifft, wird ausgeführt.
        case "Michael", "Tom", "Erik"               -> "m";
        case "Alice", "Eva", "Maria", "Eva-Maria"   -> "w";
        case String s when s.length() < 2           -> "kein Name";
        case _ when name.contains("-")              -> "Doppelname";
        default                                     -> "<unbekannt>";
    };
    IO.println("Namensanalyse = " + nameAnalysis);
}