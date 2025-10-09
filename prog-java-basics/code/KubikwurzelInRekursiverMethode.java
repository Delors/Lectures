double cbrt(final double n, double guess, int steps) {
    if (steps == 0)
        return guess;

    guess = guess - ((guess * guess * guess - n) / (3 * guess * guess));
    IO.println("guess: " + guess); 
    return cbrt(n, guess, steps - 1);
}

void main(String[] args) {
    var n = "";
    if (args.length == 0) {
        n = IO.readln("""
                Geben Sie eine Zahl n ein
                deren Kubikwurzel w Sie berechnen wollen
                (d.h. n = w*w*w): """);
    } else {
        n = args[0];
    }
    final var r = cbrt(Double.parseDouble(n), 1.0d, 100);
    IO.println("Das Ergebnis ist: " + r);
}

