public class Pair<U,V> {

    private U first;
    private V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

void main() {
    Pair<Integer,Integer> p = new Pair<>(1, 2);
    System.out.println(p);
    p.setFirst(5);
    // Would not work: p.setSecond(3.0);
    System.out.println(p);
    p.setSecond(p.getFirst() + p.getSecond());
    System.out.println(p);

    Pair<Integer,Integer> p1 = new Pair<>(36462828, 50);
    Pair<String,Integer> p2 = new Pair<>("Michael", 2023);
}