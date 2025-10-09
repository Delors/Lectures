void main(String[] args) {

    if (args.length % 2 != 0) {
        IO.println("Ungültige Anzahl an Argumenten.");
        return;
    }

    for (int i = 0; i < args.length; i += 2) {
        switch (args[i]) {
            case "fibonacci" -> {
                final var n = Integer.parseInt(args[i + 1]);
                IO.println("fiboncci(" + n + ") = " + Functions.fibonacci(n));
            }
            case "isPrim" -> {
                final var n = Integer.parseInt(args[i + 1]);
                IO.println("isPrim(" + n + ") = " + Functions.isPrim(n));
            }
            case "cbrt" -> {
                final var n = Double.parseDouble(args[i + 1]);
                IO.println(n + "^⅓ = " + Functions.cbrt(n));
            }
            default -> {
                IO.println("[error] Ungültige Funktion: " + args[i]);
                return;
            }
        }
    }
}