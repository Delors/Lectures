package de.dhbw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelloYouTest {

    // Let's redirect System.out to capture the output!
    private final PrintStream defaultOut = System.out;
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setOutputStream() {
        final var out = new PrintStream(testOut);
        System.setOut(out);
    }

    @AfterEach
    public void resetSystemOut() {
        System.setOut(defaultOut);
    }

    // TESTS

    @Test
    void testMainNoArgs() {
        HelloYou.main(new String[0]);
        assertEquals("Usage: java HelloYou <name>\n", testOut.toString());
    }

    @Test
    void testMainArg() {
        HelloYou.main(new String[] { "Bob" });
        assertEquals("Hello Bob!\n", testOut.toString());
    }
}
