record LineToElement<T>(int line, T element) {}

<T> Gatherer<T, ?, LineToElement<T>> lineToElement() {
    return Gatherer.ofSequential(
        () -> new int[] { 1 }, // to store the current line number
        (counter, element, downstream) -> {
            var l = counter[0]++;
            downstream.push(new LineToElement<>(l, element));
            return true;
        }
    );
}

@FunctionalInterface
interface IOFunction<T, R> {
    R apply(T t) throws IOException;
}

class IOExceptionHandlingGatherer<T, R> implements Gatherer<T, Void, R> {

    private static final Object lock = new Object();

    private final IOFunction<T, R> mapper;

    public IOExceptionHandlingGatherer(IOFunction<T, R> mapper) {
        this.mapper = mapper;
    }

    public Gatherer.Integrator<Void, T, R> integrator() {
        return (_, element, downstream) -> {
            try {
                downstream.push(mapper.apply(element));
            } catch (IOException e) {
                synchronized (lock) {
                    System.err.print("Ignoring: ");
                    System.err.println(element);
                }
            }
            return true;
        };
    }
}

void main(String[] args) throws Exception {
    if (args.length != 2) {
        IO.println("Usage: find <search-term> <file-extension>");
        return;
    }
    var searchTerm = args[0];
    var extension = args[1];

    try (var files = Files.walk(new File(".").toPath())) {
        files
            .unordered()
            .parallel()
            .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(extension))
            /* .map(p -> {
                // Explicit Exception Handling in the Lambda:
                try (var lines = Files.lines(p)) {
                    return lines
                        .gather(lineToElement())
                        .filter(x -> x.element().contains(searchTerm))
                        .map(l -> p + ":" + l.line() + ": " + l.element())
                        .findAny(); // => Optional<String>
                } catch (Exception e) {
                    // Here, we rely on the de facto thread safety of
                    // System.err.println, which is - however - not guaranteed!
                    System.err.println("Ignoring: " + p);
                    return Optional.empty();
                }
            }) */
            .gather(
                new IOExceptionHandlingGatherer<Path, Optional<String>>(p -> {
                    try (var lines = Files.lines(p)) {
                        return lines
                            .gather(lineToElement())
                            .filter(x -> x.element().contains(searchTerm))
                            .map(l -> p + ":" + l.line() + ": " + l.element())
                            .findAny();
                    }
                })
            )
            .flatMap(Optional::stream)
            .sequential() // required to avoid a convoluted output
            .forEach(System.out::println);
    }
}
