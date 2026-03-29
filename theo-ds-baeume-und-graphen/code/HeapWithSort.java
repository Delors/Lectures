import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

class Heap<T extends Comparable<T>> {

    // Min-heap!

    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(Class<T> componentType, int capacity) {
        heap = (T[]) Array.newInstance(componentType, capacity);
        size = 0;
    }

    public boolean nonEmpty() {
        return size != 0;
    }

    public void insert(T element) {
        int i = size;
        heap[size++] = element;

        while (i > 0 && heap[(i - 1) / 2].compareTo(heap[i]) > 0) {
            var parent = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = heap[i];
            heap[i] = parent;
            i = (i - 1) / 2;
        }
    }

    @SafeVarargs
    final void insertAll(T... args) {
        for (T arg : args) {
            insert(arg);
        }
    }

    public T remove() {
        if (size == 0) throw new NoSuchElementException();

        final T next = heap[0];
        T v = heap[--size];
        heap[size] = null; // avoid memory leak
        heap[0] = v;
        int i = 0;
        while (i < (size / 2)) {
            int j = 2 * i + 1;
            // Find the correct leaf:
            if (j < size - 1 && heap[j].compareTo(heap[j + 1]) > 0) j++;
            if (v.compareTo(heap[j]) < 0) break;
            heap[i] = heap[j];
            i = j;
        }
        heap[i] = v;
        return next;
    }

    // sort: Start
    public static <X extends Comparable<X>> List<X> sort(
        Class<X> elementType /*Alternative names: evidence, witness, type */,
        Collection<? extends X> collection
    ) {
        Heap<X> heap = new Heap<>(elementType, collection.size());
        for (X x : collection) {
            heap.insert(x);
        }

        List<X> result = new ArrayList<>(collection.size());
        while (heap.nonEmpty()) {
            result.add(heap.remove());
        }
        return result;
    }

    // sort: End

    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(String.class, 5);
        //heap.insertAll("a", "d", "f", "b", "c");
        heap.insertAll("dies", "ist", "ein", "wichtiger", "test");

        while (heap.nonEmpty()) {
            String s = heap.remove();
            IO.println(s);
        }

        var sorted = Heap.sort(Integer.class, List.of(3, 5, 6, 2, 4, 5, 1, 2, 4));
        IO.println(sorted);

        List<java.sql.Date> dates = List.of(
            new java.sql.Date(24234234234234l),
            new java.sql.Date(10948093483l)
        );
        var sortedDates = Heap.<java.util.Date>sort(java.util.Date.class, dates);
        IO.println(sortedDates);
    }
}
