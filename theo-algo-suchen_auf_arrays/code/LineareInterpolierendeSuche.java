// Ausführen: java LineareInterpolierendeSuche.java
import static java.lang.Math.*;

// Gibt den Index von needle in A zurück, oder -1 falls nicht gefunden.
// A ist 0-indiziert (Java-Standard), aufsteigend sortiert.
int search(int[] A, int needle) {
    int lower = 0;
    int upper = A.length - 1;

    int vL = A[lower];
    if (vL == needle) return lower;

    int vU = A[upper];
    if (vU == needle) return upper;

    while (upper > lower) {
        // Lineare Interpolation der geschätzten Position
        int pos = (int) round(
            lower * (double)(needle - vU) / (vL - vU) +
            upper * (double)(needle - vL) / (vU - vL)
        );

        pos = max(lower + 1, min(upper - 1, pos));

        int value = A[pos];
        if      (value == needle) return pos;
        else if (value  < needle) { lower = max(pos, lower+1); vL = A[lower]; }
        else                      { upper = min(pos, upper-1); vU = A[upper]; }
    }
    return -1; // nil
}

void suche(int[] A, int needle) {
    int idx = search(A, needle);
    if (idx == -1)
        System.out.printf("needle = %4d  ->  nicht gefunden%n", needle);
    else
        System.out.printf("needle = %4d  ->  Index %d  (A[%d] = %d)%n",
                            needle, idx, idx, A[idx]);
}

void main() {
    // Beispiel-Array aus der Diskussion: 4x² für x = 0..7
    int[] A = {0, 4, 16, 36, 64, 100, 144, 196};

    IO.println("Array: [0, 4, 16, 36, 64, 100, 144, 196]  (= 4x² fuer x = 0..7)");
    IO.println();

    suche(A,   0);   // Randfall: kleinstes Element
    suche(A, 196);   // Randfall: größtes Element
    suche(A,  64);   // mittleres Element
    suche(A,  36);   // weiteres Element
    suche(A, 194);   // nicht enthalten (Beispiel aus der Diskussion)
    suche(A,  50);   // nicht enthalten, innerhalb
    suche(A,  -1);   // unterhalb des Arrays
    suche(A,  -2);   // unterhalb des Arrays
    suche(A, 999);   // oberhalb des Arrays
}

    