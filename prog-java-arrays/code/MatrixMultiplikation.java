/**
 * Multipliziert zwei Matrizen.
 * @param a Matrix a mit Größe m (Zeilen) x n (Spalten). 
 *          (Alle Zeilen müssen die gleiche Länge haben.)
 * @param b Matrix b mit Größe n (Zeilen) x o (Spalten). 
 *          (Alle Zeilen müssen die gleiche Länge haben.)
 * @return Die Matrix mit Größe m x o.
 */
int[][] multiply(final int[][] a, final int[][] b) {
    final int[][] c = new int[a.length][b[0].length];

    // Die äußerste Schleife iteriert über die Zeilen von a/
    //                                     die Zeilen der Ergebnismatrix
    for (int i = 0; i < a.length; i++) {
        assert a[i].length == b.length; // Anzahl Spalten von a[i] == Anzahl Zeilen von b

        // Die mittlere Schleife iteriert über die Spalten von b/
        //                                     die Spalten der Ergebnismatrix
        for (int j = 0; j < b[0].length; j++) {
            assert b[j].length == b[0].length; // alle Zeilen haben die gleiche Länge
            // Verrechnung der Zeile i von a mit der Spalte j von b
            for (int k = 0; k < b.length; k++) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

void printToConsole(int [][] c) {
    //println(Arrays.deepToString(c));
    for (int i = 0; i < c.length; i++) {
        IO.println(Arrays.toString(c[i]));
    }
}

int[][] initMatrix(int[][] a,String name) {
    for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a[i].length; j++) {
            a[i][j] = Integer.parseInt(IO.readln(name+"[" + i + "][" + j + "] = "));
        }
    }
    return a;
}

void main() {
    /*
    final int[][] a = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {10, 11, 12}
    };
    final int[][] b = {
        {9, 8, 7},
        {6, 5, 4},
        {3, 2, 7}
        // , {12, 11, 10} ==> AssertionError!
    };

    final var c = multiply(a, b);
    printToConsole(c);
    */

    int m = Integer.parseInt(IO.readln("Anzahl der Zeilen von Matrix a: "));
    int n = Integer.parseInt(IO.readln("Anzahl der Spalten von Matrix a: "));
    int o = Integer.parseInt(IO.readln("Anzahl der Spalten von Matrix b: "));

    final int[][] a = initMatrix(new int[m][n],"a");
    final int[][] b = initMatrix(new int[n][o],"b");
    final var c = multiply(a, b);
    printToConsole(c);
}