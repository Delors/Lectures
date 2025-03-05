package ds;

import java.util.NoSuchElementException;

public class Stack<T> extends List<T> {

    public void push(T element) {
        add(element);
    }

    public T pop() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T element = get(size() - 1);
        remove(size() - 1);
        return element;
    }

    public T peek() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return get(size() - 1);
    }
}
