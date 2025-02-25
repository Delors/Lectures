package ds;

import java.util.NoSuchElementException;

public class Stack extends List {

    public void push(Object element) {
        add(element);
    }

    public Object pop() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Object element = get(size() - 1);
        remove(size() - 1);
        return element;
    }

    public Object peek() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return get(size() - 1);
    }
}
