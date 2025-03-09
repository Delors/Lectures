package ds.generic;

import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Queue<T> {
    
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
    int size();

    <X> Queue<X> map(Function<T, X> mapper);
    void replaceAll(UnaryOperator<T> operator);
    void forEach(Consumer<T> operator);

    static class TheQueue<T> implements Queue<T>{
        
        class QueueElement {
            private T t;
            private QueueElement next;
            QueueElement(T t) {
                this.t = t;
            }
        }

        private QueueElement first;
        private QueueElement last;

        TheQueue() {
            this.first = null;
            this.last = null;
        }

        @Override
        public void enqueue(T t) {
            if(first == null) {
                first = new QueueElement(t);
                last = first;
                return;
            }
            last = (last.next = new QueueElement(t));
        }

        @Override
        public T dequeue() {
            if (first == null) {
                throw new IllegalStateException("Queue is empty");
            }
            T t = first.t;
            first = first.next;
            return t;
        }

        @Override
        public boolean isEmpty() {
            return first == null;
        }

        @Override
        public int size() {
            int size = 0;
            for (QueueElement e = first; e != null; e = e.next) {
                size++;
            }
            return size;
        }

        @Override
        public void replaceAll(UnaryOperator<T> operator) {
            for (QueueElement e = first; e != null; e = e.next) {
                e.t = operator.apply(e.t);
            }
        }

        @Override
        public void forEach(Consumer<T> operator) {
            for (QueueElement e = first; e != null; e = e.next) {
                operator.accept(e.t);
            }
        }

        @Override
        public <X> Queue<X> map(Function<T, X> mapper) {
            Queue<X> result = new TheQueue<>();
            for (QueueElement e = first; e != null; e = e.next) {
                result.enqueue(mapper.apply(e.t));
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (QueueElement e = first; e != null; e = e.next) {
                sb.append(e.t);
                if (e.next != null) {
                    sb.append(", ");
                }
            }
            sb.append(")");
            return sb.toString();
        }

    }

    static <T> Queue<T> empty() {
        return new TheQueue<T>();
    }
}
