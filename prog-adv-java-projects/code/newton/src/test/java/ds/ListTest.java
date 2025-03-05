package ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ListTest {
 
    @Test
    void testRemove() {
        var random = new java.util.Random();
        List<Integer> stack = new List<>();
        for (int i = 0; i < 48; i++) {
            stack.add(i);
        }
        for (int i = 0; i < 48; i++) {
            stack.remove(random.nextInt(48-i));
            assertEquals(47-i, stack.size());
        }
    }

    @Test
    void testAddAll() {
        List<Integer> stack = new List<>();
        stack.addAll(1, 2, 3);
        assertEquals(3, stack.size());
        assertEquals(1, stack.get(0));
        assertEquals(2, stack.get(1));
        assertEquals(3, stack.get(2));
    }

    @Test
    void testSet() {
        List<Integer> stack = new List<>();
        stack.addAll(1, 2, 3);
        stack.set(1, 4);
        assertEquals(4, stack.get(1));
        assertEquals(3, stack.size());
    }

    @Test
    void testToString() {
        List<Integer> stack = new List<>();
        stack.addAll(1, 2, 3);
        assertEquals("[1, 2, 3]", stack.toString());
    }
}
