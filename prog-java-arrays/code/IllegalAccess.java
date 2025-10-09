void main() {
    final int[] a = {1, 2, 3};
    IO.println(a[0] = -1);
    IO.println(Arrays.toString(a));
    // a = new int[]{}; // IllegalAccess.java: 
                        // error: cannot assign a value to final variable a
}
