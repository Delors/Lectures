import java.util.Arrays;
import java.util.List;

class Lambdas {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve");

        // TODO 1: Using a lambda, sort the names alphabetically
        names.sort((a, b) -> a.compareTo(b));

        // TODO 2: Print the names to the console using forEach and a Method reference.
        names.forEach(System.out::println);
    }
}
