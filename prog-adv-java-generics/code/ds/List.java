package ds;

public class List {

    private Object[] elements;
    private int count;

    public List(int size) {
        elements = new Object[Math.max(size, 16)];
        count = 0;
    }

    public List(){
        this(16);
    }

    public int size() {
        return count;
    }

    public Object get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }


    public void add(Object element) {
        if (count == elements.length) {
            Object[] newElements = new Object[Math.min(elements.length * 2,1000)];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[count++] = element;
    }

    public void addAll(Object... elements) {
        for (Object element : elements) {
            add(element);
        }
    }

    public void set(int index, Object element) {
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
            Object[] newElements = new Object[Math.max(elements.length / 2, 16)];
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

