public class Pair implements Iterable{

    private Object first;
    private Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    public Iterator iterator() {
        return new PairIterator(this);
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public void setSecond(Object second) {
        this.second = second;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}


class PairIterator implements Iterator {

        private Pair pair;
        private int index;

        public PairIterator(Pair pair) {
            this.pair = pair;
            this.index = 0;
        }

        public boolean hasNext() {
            return index < 2;
        }

        public Object next() {
            if (index == 0) {
                index++;
                return pair.getFirst();
            } else if (index == 1) {
                index++;
                return pair.getSecond();
            } else {
                return null;
            }
        }
    }

void main() {
    Pair p = new Pair(1, 2);
    System.out.println(p);
    p.setFirst("Hello");
    p.setSecond("World");
    System.out.println("Typ des ersten Wertes im Paar: " + p.getFirst().getClass());
    System.out.println(p);
    p.setFirst(3.14);
    p.setSecond(2.71);
    System.out.println(p);
    p.setFirst(1);
    p.setSecond(2);
    p.setSecond((int) p.getFirst() + (int) p.getSecond());
    System.out.println(p.getSecond());

    System.out.print("Jetzt mit Iterator: ");
    for(Object o : p) {
        System.out.print(o+ " ");
    }
    System.out.print("- fertig.");
}