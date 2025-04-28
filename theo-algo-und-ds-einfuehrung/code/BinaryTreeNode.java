import java.util.function.Consumer;

public class Node<T extends Comparable<T>> {

    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void forEach(Consumer<T> f) {
        if (left != null) {
            left.forEach(f);
        }
        f.accept(data);
        if (right != null) {
            right.forEach(f);
        }
    }

    public int size() {
        int[] size = { 0 };
        forEach(e -> size[0]++);
        return size[0];
    }

    public void insert(T value) {
        if (value.compareTo(data) <= 0) {
            if (left == null) {
                left = new Node<>(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node<>(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(T value) {
        if (value.compareTo(data) == 0) {
            return true;
        } else if (value.compareTo(data) <= 0) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(data);
        if (right != null) {
            right.printInOrder();
        }
    }

    public void printPreOrder() {
        System.out.println(data);
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.println(data);
    }

    public static void main(String[] args) {
        var root = new Node<Integer>(10);
        root.insert(5);
        root.insert(15);
        root.insert(8);
        root.insert(3);
        root.insert(7);
        root.insert(12);
        root.insert(18);

        System.out.println(root.contains(7));
        System.out.println(root.contains(17));

        System.out.println("In Order");
        root.printInOrder();

        System.out.println("Pre Order");
        root.printPreOrder();

        System.out.println("Post Order");
        root.printPostOrder();

        System.out.println("In Order using forEach");
        root.forEach(System.out::println);

        System.out.println("Size: " + root.size());
    }
}
