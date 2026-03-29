class Heap<T extends Comparable<T>> {

    // Min-heap!

    private T[] heap;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(Class<T> componentType, int capacity) {
        heap = (T[]) Array.newInstance(componentType, capacity);
        size = 0;
    }

    // TODO nonEmpty

    // TODO insert

    public T remove() {
        throw new UnsupportedOperationException("remove is not implemented yet");
    }

    @SafeVarargs
    final void insertAll(T... args) {
        for (T arg : args) {
            insert(arg);
        }
    }
}

void main() {
    Heap<String> heap = new Heap<>(String.class, 5);
    heap.insertAll("dies", "ist", "ein", "wichtiger", "test");
    while (heap.nonEmpty()) {
        String s = heap.remove();
        IO.println(s);
    }
}
