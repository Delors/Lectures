import math.Functions;

void main(String[] args) {

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
        }
    }

    Functions.cbrt(100);
}