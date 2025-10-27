public class BinarySearchTree {
    private static class Node {
        int value;
        Node left, right;
        Node(int v) { value = v; }
    }

    private Node root;
    private int size = 0;

    // Додати елемент (<= праворуч за правилом з ПР)
    public void add(int v) { root = insert(root, v); }

    private Node insert(Node n, int v) {
        if (n == null) { size++; return new Node(v); }
        if (v < n.value) n.left = insert(n.left, v);
        else             n.right = insert(n.right, v);
        return n;
    }

    // Перевірити наявність
    public boolean contains(int v) {
        Node n = root;
        while (n != null) {
            if (v == n.value) return true;
            n = (v < n.value) ? n.left : n.right;
        }
        return false;
    }

    // Видалити елемент
    public void remove(int v) { root = delete(root, v); }

    private Node delete(Node n, int v) {
        if (n == null) return null;
        if (v < n.value) n.left = delete(n.left, v);
        else if (v > n.value) n.right = delete(n.right, v);
        else {
            // 0 або 1 дитина
            if (n.left == null && n.right == null) { size--; return null; }
            if (n.left == null) { size--; return n.right; }
            if (n.right == null) { size--; return n.left; }
            // 2 дитини: мінімум з правого піддерева
            Node succ = minNode(n.right);
            n.value = succ.value;
            n.right = delete(n.right, succ.value); // фактичне видалення + size--
        }
        return n;
    }

    // Мінімум / Максимум
    public int min() {
        if (root == null) throw new IllegalStateException("Дерево порожнє");
        return minNode(root).value;
    }
    private Node minNode(Node n) { while (n.left != null) n = n.left; return n; }

    public int max() {
        if (root == null) throw new IllegalStateException("Дерево порожнє");
        return maxNode(root).value;
    }
    private Node maxNode(Node n) { while (n.right != null) n = n.right; return n; }

    // Порахувати, очистити
    public int count() { return size; }
    public int size()  { return size; }
    public void clear() { root = null; size = 0; }

    // Вивести всі елементи (in-order)
    @Override public String toString() {
        if (root == null) return "In-order: []";
        StringBuilder sb = new StringBuilder("In-order: [");
        inOrder(root, sb);
        if (sb.length() >= 2) sb.setLength(sb.length() - 2); // прибрати останнє ", "
        sb.append("]");
        return sb.toString();
    }
    private void inOrder(Node n, StringBuilder sb) {
        if (n == null) return;
        inOrder(n.left, sb);
        sb.append(n.value).append(", ");
        inOrder(n.right, sb);
    }

    // Демонстрація
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] vals = {7,3,9,1,5,8,10,6,4,7};
        for (int v : vals) bst.add(v);
        System.out.println(bst);
        System.out.println("Мін: " + bst.min() + ", Макс: " + bst.max());
        System.out.println("Є 5? " + bst.contains(5));
        bst.remove(3);
        System.out.println("Після видалення 3: " + bst);
        System.out.println("Кількість: " + bst.size());
        bst.clear();
        System.out.println("Після clear(): " + bst + ", size=" + bst.size());
    }
}
