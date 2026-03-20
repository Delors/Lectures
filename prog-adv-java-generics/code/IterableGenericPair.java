import java.util.NoSuchElementException;

public record Pair<U>(U f, U s) implements Iterable<U> {
    public Iterator<U> iterator() {
        return new Iterator<U>() {
            int index = 0;

            public boolean hasNext() {
                return index < 2;
            }

            public U next() {
                return switch (index) {
                    case 0: {
                        index++;
                        yield f;
                    }
                    case 1: {
                        index++;
                        yield s;
                    }
                    default:
                        throw new NoSuchElementException();
                };
            }
        };
    }
}

void main() {
    Pair<Integer> p = new Pair<>(1, 2);
    for (int i : p) {
        IO.println(i);
    }
}
