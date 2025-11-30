public class Util {
    static void printElement(int[] a, long b) {
        System.out.println(a[(int) b]);
    }
}

public class Bugs {

    public static void main(String[] args) {
        int[] a = { 10, 20, 30 };
        Util.printElement(a, Integer.parseInt(args[0]));
    }
}
