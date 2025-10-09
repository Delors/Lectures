import static math.Functions.cbrt;
import static math.Functions.fibonacci;
import static math.Functions.isPrim;

void main(String[] args) {

    for (int i = 0; i < args.length; i += 2) {
        switch (args[i]) {
            case "fibonacci" -> {
                final var n = Integer.parseInt(args[i + 1]);
                IO.println("fiboncci(" + n + ") = " + fibonacci(n));
            }
            case "isPrim" -> {
                final var n = Integer.parseInt(args[i + 1]);
                IO.println("isPrim(" + n + ") = " + isPrim(n));
            }
            case "cbrt" -> {
                final var n = Double.parseDouble(args[i + 1]);
                IO.println(n + "^⅓ = " + cbrt(n));
            }
        }
    }
}