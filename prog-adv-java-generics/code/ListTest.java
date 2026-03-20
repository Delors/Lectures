import ds.List;

public class ListTest {

    public static void main() {

        List list = new List();
        list.add(1);
        list.add("Hello");
        list.add(3.14);
        list.add(4);
        System.out.println(list + " [size=" + list.size() + "]");
        list.remove(0);
        System.out.println(list.get(0));
        list.set(0, "0Grad");
        System.out.println(list);
        list.addAll(1, 2.3, 3f, new Object());
        System.out.println(list);

        List list2 = new List(1000);
        for (int i = 0; i < 10000; i++) {
            list2.add(i);
        }
        System.out.println(list2);

        for (int i = 0; i < 9990; i++) {  
            var index = (int) (Math.random() * list2.size());
            IO.println("Removing element at random index: " + index);   
            list2.remove(index);
        }
        System.out.println(list2);
    }

}