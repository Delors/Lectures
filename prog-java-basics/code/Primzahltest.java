void main() {
    final var n = Integer.parseInt(IO.readln("Geben Sie eine ganze positive Zahl ein? "));
 
    if (n < 2) {
        IO.println("Keine Primzahl");
        return;
    }

    int i = 2;
    for (; i < n/2; i++) {
        if (n % i == 0) {
            break;
        }
    }

    if (i >= n/2) {
        System.out.println(n +" ist eine Primzahl");
    } else {
        System.out.println(i + " ist ein Teiler von "+ n);
    }
    
}
