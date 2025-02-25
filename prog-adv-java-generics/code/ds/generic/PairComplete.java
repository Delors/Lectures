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

    public void updateFrom(Pair<? extends U,? extends V> other) {
        this.first = other.first;
        this.second = other.second;
    }

    public void addToMap(java.util.Map<? super U, ? super V> map) {
        map.put(first, second);
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

    java.util.Map<Object,Integer> map = new java.util.HashMap<>();
    p1.addToMap(map);
    System.out.println(map);


    Pair<Object,Object> pObject = new Pair<>("a",new Object());
    pObject.update(p1);
    System.out.println(pObject);
}