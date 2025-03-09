package ds.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class Tuple2Test {

    // TESTS

    @Test
    void testForEach() {
        var pair = new Tuple2<>(1, 2);
        var result = new StringBuilder();
        pair.forEach(result::append);
        assertEquals("12", result.toString());
    }

    @Test
    void testReplaceAll() {
        var pair = new Tuple2<>(1, 2);
        pair.replaceAll(x -> x + 1);
        assertEquals("(2, 3)", pair.toString());
    }
}
