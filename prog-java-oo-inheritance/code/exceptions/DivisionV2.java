import static java.lang.System.err;
void main(String[] args) {
    try {
        int i = Integer.parseInt(args[0]);
        int j = Integer.parseInt(args[1]);
        try {
            IO.println(i / j);
        } catch (ArithmeticException e) {
            err.println("Division durch 0 nicht erlaubt.");
        }
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        err.println("Argumente falsch.");
}   }