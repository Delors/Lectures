/**
 * Testet ob eine Zahl eine Primzahl ist.
 * 
 * Die Laufzeit ist O(n/4).
 * 
 * @param n Eine positive ganze Zahl.
 * @return true, wenn n eine Primzahl ist, sonst false.
 */
boolean isPrim(int n) {
    if (n < 2) {
        return false;
    }

    boolean isPrim = true;    
    for (int i = 2; i <= n / 2; i++) {
        if (n % i == 0) {
            isPrim = false;
            break;
        }
    }
    return isPrim;
}

void main() {
    final var n = Integer.parseInt(IO.readln("Geben Sie eine ganze positive Zahl ein? "));
    if (isPrim(n)) {
        System.out.println(n + " ist eine Primzahl");
    } else {
        System.out.println(n + " ist keine Primzahl");
    }
}
