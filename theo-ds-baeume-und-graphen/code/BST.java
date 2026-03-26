import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

    private class Node {

        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.count = 1;
        }
    }

    private Node root;

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp == 0) x.val = val;
        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return x.val;
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public Key select(int k) {
        // returns the nth-largest key
        if (k < 0) return null;
        if (k >= size()) return null;
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return (Node) null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else /* if (t == k)*/ return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            // Update the current node with the minimum value from the
            // right subtree
            Node m = min(x.right);
            x.key = m.key;
            x.val = m.val;
            // Remove the minimum node from the right subtree
            if (x.right == m) {
                x.right = m.right;
            } else {
                deleteMinNode(x.right, m);
            }
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void deleteMinNode(Node x, Node m) {
        assert x != m;
        if (x.left == m) x.left = m.right;
        else deleteMinNode(x.left, m);
    }

    // EXERCISE SOLUTION

    public Value min() {
        return root == null ? null : min(root).val;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    public Iterable<Key> descending() {
        Deque<Key> q = new LinkedList<Key>();
        descending(root, q);
        return q;
    }

    private void descending(Node x, Deque<Key> q) {
        if (x == null) return;
        descending(x.right, q);
        q.add(x.key);
        descending(x.left, q);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(Node x, StringBuilder sb) {
        if (x == null) return;
        sb.append("( ");
        toString(x.left, sb);
        sb.append(x.key).append(" ");
        toString(x.right, sb);
        sb.append(" )");
    }

    public static void main(String[] args) {
        var bst = new BST<Integer, String>();
        bst.put(1, "1.");
        bst.put(4, "4.");
        bst.put(2, "2.");
        bst.put(3, "3.");
        bst.put(-1, "-1.");
        bst.put(5, "5.");
        IO.println("Size: " + bst.size() + " - Min: " + bst.min() + " - Tree: " + bst);
        IO.println(bst.select(0));
        IO.println(bst.select(1));
        IO.println(bst.select(2));
        IO.println(bst.select(3));

        IO.println("Descending:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }

        bst.delete(4);
        IO.println("After deleting 4:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
        bst.delete(1);
        IO.println("After deleting 1:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
        bst.delete(3);
        IO.println("After deleting 3:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
        bst.delete(-1);
        IO.println("After deleting -1:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
        bst.delete(5);
        IO.println("After deleting 5:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
        bst.delete(2);
        IO.println("After deleting 2:");
        for (Integer v : bst.descending()) {
            IO.println(v);
        }
    }
}
