import static java.lang.Math.*;

import java.util.NoSuchElementException;

long binarySearch(DoubleUnaryOperator f, long l, long u, double needle) {
    while (u >= l) {
        long pos = (u + l) / 2;
        double value = f.applyAsDouble(pos);
        if (value == needle) {
            return pos;
        } else if (value > needle) {
            u = pos - 1;
        } else {
            l = pos + 1;
        }
    }

    throw new NoSuchElementException(needle + " nicht gefunden.");
}

long exponentialSearch(DoubleUnaryOperator f, double needle) {
    long i = 1; // endless loop if i == 0 !
    while (f.applyAsDouble(i) < needle) {
        IO.println(
            "exponentielle Suche - index: " + i + "; Wert: " + f.applyAsDouble(i)
        );
        i *= 2d;
    }
    return binarySearch(f, i / 2 + 1, i, needle);
}

void main() {
    try {
        IO.println(
            "Wert 64 hat Index: " + binarySearch((double x) -> 4 * x + 3, 0, 100, 64)
        );
    } catch (NoSuchElementException e) {
        IO.println("Wert 64 nicht gefunden.");
    }
    IO.println(binarySearch((double x) -> (4 * x + 3), 0, 100, 43));

    try {
        IO.println(
            "Wert 99999996 hat Index: " +
                exponentialSearch(
                    (double x) -> round((1.0 / (1 + pow(E, -x))) * 100_000_000d),
                    99999996
                )
        );
    } catch (NoSuchElementException e) {
        IO.println("Wert 99999996  nicht gefunden.");
    }
}
