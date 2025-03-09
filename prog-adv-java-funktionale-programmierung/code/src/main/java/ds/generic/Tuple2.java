package ds.generic;

import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class Tuple2<T> {

    private T first;
    private T second;

    public Tuple2(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public void replaceAll(UnaryOperator<T> operator) {
        first = operator.apply(first);
        second = operator.apply(second);
    }   

    public void forEach(Consumer<T> operator) {
        operator.accept(first);
        operator.accept(second);
    }

    public <X> Tuple2<X> map(Function<T,X> mapper) {
        return new Tuple2<>(mapper.apply(first), mapper.apply(second));
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

