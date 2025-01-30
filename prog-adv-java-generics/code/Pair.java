

public class Pair {

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




void main() {
    Pair p = new Pair(1, 2);
    System.out.println(p);
    p.setFirst("Hello");
    p.setSecond("World");
    System.out.println(p);
    p.setFirst(3.14);
    p.setSecond(2.71);
    System.out.println(p);
    p.setFirst(1);
    p.setSecond(2);
    p.setSecond((int) p.getFirst() + (int) p.getSecond());
    System.out.println(p.getSecond());  
}