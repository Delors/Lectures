package ds.generic;

public class List<T> {

    private T[] elements;
    private int count;

    public List(int size) {
        // Generics were added relatively lately to Java, so arrays of generic types do not have complete support.
        @SuppressWarnings("unchecked")
        var tElements = (T[]) new Object[Math.max(size, 16)];
        elements = tElements;
        count = 0;
    }

    public List(){
        this(16);
    }

    public int size() {
        return count;
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }


    public void add(T element) {
        if (count == elements.length) {
            Object[] newElements = new Object[Math.min(elements.length * 2,1000)];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            @SuppressWarnings("unchecked")
            var tElements = (T[]) newElements; // to ensure that our suppressWarning only applies to this line, we have to assign the new array to a variable which can be annotated.
            elements = tElements;
        }
        elements[count++] = element;
    }

    @SafeVarargs
    final public void addAll(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public void set(int index, T element) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        if (count < elements.length/4 && elements.length > 16) {
            @SuppressWarnings("unchecked")
            T[] newElements = (T[]) new Object[Math.max(elements.length / 2, 16)];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index + 1, newElements, index, count - index - 1);
            elements = newElements;
            count--;
        }
        else {
            System.arraycopy(elements, index + 1, elements, index, count - index - 1);
            count--;
        }
    }

}

