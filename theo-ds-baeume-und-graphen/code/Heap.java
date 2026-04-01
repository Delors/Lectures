//import java.lang.reflect.Array;
//import java.util.NoSuchElementException;

import module java.base;

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

    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(String.class, 5);
        //heap.insertAll("a", "d", "f", "b", "c");
        heap.insertAll("dies", "ist", "ein", "wichtiger", "test");

        while (heap.nonEmpty()) {
            String s = heap.remove();
            IO.println(s);
        }
    }
}
