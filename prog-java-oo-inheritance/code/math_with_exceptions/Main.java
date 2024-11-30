import math.Functions;

void main(String[] args) {

    try {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "fibonacci" -> {
                    final var n = Integer.parseInt(args[i + 1]);
                    println("fiboncci(" + n + ") = " + Functions.fibonacci(n));
                }
                case "isPrim" -> {
                    final var n = Integer.parseInt(args[i + 1]);
                    println("isPrim(" + n + ") = " + Functions.isPrim(n));
                }
                case "cbrt" -> {
                    final var n = Double.parseDouble(args[i + 1]);
                    println(n + "^⅓ = " + Functions.cbrt(n));
                }
            }
        }
    } catch (IllegalArgumentException e) {
        println("Error: " + e.getMessage());
    }
}