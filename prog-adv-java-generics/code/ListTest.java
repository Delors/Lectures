import ds.List;

public class ListTest {

    public static void main() {

        List list = new List();
        list.add(1);
        list.add("Hello");
        list.add(3.14);
        list.add(4);        
        System.out.println(list+" [size="+list.size()+"]");
        list.remove(0);
        System.out.println(list.get(0));
        list.set(0, "0Grad");
        System.out.println(list);
        list.addAll(1,2.3,3f,new Object());
        System.out.println(list);
    }

}