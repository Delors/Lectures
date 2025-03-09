package ds.generic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class QueueTest {
    
    // TESTS

    @Test
    void testEnqueue() {
        Queue<Integer> queue = Queue.empty();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
    }

    @Test
    void testDequeue() {
        Queue<Integer> queue = Queue.empty();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    void testIsEmpty() {
        Queue<Integer> queue = Queue.empty();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testSize() {
        Queue<Integer> queue = Queue.empty();
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    void testMap() {
        Queue<Integer> queue = Queue.empty();
        queue.enqueue(1);
        queue.enqueue(2);
        Queue<String> mapped = queue.map(Object::toString);
        assertEquals("(1, 2)", queue.toString());
        assertEquals("(1, 2)", mapped.toString());
    }

    @Test
    void testReplaceAll() {
        Queue<Integer> queue = Queue.empty();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.replaceAll(x -> x + 1);
        assertEquals("(2, 3)", queue.toString());
    }

    @Test
    void testForEach() {
        Queue<Integer> queue = Queue.empty();
        queue.enqueue(1);
        queue.enqueue(2);
        var result = new StringBuilder();
        queue.forEach(result::append);
        assertEquals("12", result.toString());
    }
}
