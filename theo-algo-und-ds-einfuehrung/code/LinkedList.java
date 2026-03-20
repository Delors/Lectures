import java.util.NoSuchElementException;

class LinkedList<V> implements Iterable<V> {

    private record Node<V>(Node<V> next, V value) {}

    private Node<V> root = null;

    public void prepend(V value) {
        root = new Node<V>(root, value);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public V head() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return root.value;
    }

    public void deleteHead() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        root = root.next;
    }

    public Iterator<V> iterator() {
        return new Iterator<V>() {
            Node<V> next = root;

            public boolean hasNext() {
                return next != null;
            }

            public V next() {
                var current = next;
                next = current.next;
                return current.value;
            }
        };
    }
}

void main() {
    var l = new LinkedList<String>();
    l.prepend("Test");
    l.prepend("Hallo");
    for (var s : l) {
        IO.println(s);
    }
    l.deleteHead();
    for (var s : l) {
        IO.println(s);
    }
    l.deleteHead();
    try {
        l.deleteHead();
        IO.println("This is not expected!");
    } catch (NoSuchElementException e) {
        IO.println("Done!");
    }
}
