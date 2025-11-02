// Berechnung von E über Taylorreihe
double fak(int n) {
    if (n == 0)
        return 1.0;
    else
        return n * fak(n - 1);
}

double _E(double v, int step, double precision) {
    IO.println(v + "   " + step + "   [" + precision + "]");
    var newV = v + 1.0 / fak(step);
    if (Math.abs(v - newV) < precision) {
        return newV;
    } else {
        return _E(newV, ++step, precision);
    }
}

double E(double precision) {
    return _E(1, 1, precision);
}

void main() {
    var decimalPlaces = Integer.parseInt(IO.readln("Enter number of decimal places of accuracy (> 0):"));
    var precision = 0.1d;
    for (int i = 0; i < decimalPlaces - 1; i++) {
        precision *= 0.1d;
    }
    IO.println("E = " + (E(precision)));
}