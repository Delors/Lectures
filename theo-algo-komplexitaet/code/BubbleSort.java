public void bubbleSort(int[] a) {
    for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a.length - i - 1; j++) {
            if (a[j] > a[j + 1]) {
                // swap
                int temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
            }
        }
    }
}

void main() {
    // Test the bubbleSort function
    {
        var a = new int[] { 5, 4, 3, 2, 1 };
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
    {
        var a = new int[] { 5, 7, 4, 3, -1, 2, 1 };
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}