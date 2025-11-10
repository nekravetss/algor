// DoublyLinkedList.java
// Реалізація двобічно зв'язного списку з усіма методами з ПР10.
// Мова: Java 17+
// Компіляція і запуск демо:
//   javac DoublyLinkedList.java
//   java Main

import java.util.*;

public class DoublyLinkedList<T extends Comparable<? super T>> {

    private static final class Node<U> {
        U value;
        Node<U> prev, next;
        Node(U v) { this.value = v; }
    }

    private Node<T> head, tail;
    private int size = 0;

    // --- базові операції ---
    public int size() { return size; }

    public void addFirst(T value) {
        Node<T> n = new Node<>(value);
        n.next = head;
        if (head != null) head.prev = n; else tail = n;
        head = n;
        size++;
    }

    public void addLast(T value) {
        Node<T> n = new Node<>(value);
        n.prev = tail;
        if (tail != null) tail.next = n; else head = n;
        tail = n;
        size++;
    }

    public T removeFirst() {
        ensureNotEmpty();
        Node<T> n = head;
        head = n.next;
        if (head != null) head.prev = null; else tail = null;
        size--;
        return n.value;
    }

    public T removeLast() {
        ensureNotEmpty();
        Node<T> n = tail;
        tail = n.prev;
        if (tail != null) tail.next = null; else head = null;
        size--;
        return n.value;
    }

    public boolean contains(T value) {
        for (Node<T> c = head; c != null; c = c.next) {
            if (Objects.equals(c.value, value)) return true;
        }
        return false;
    }

    public T get(int index) {
        return nodeAt(index).value;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }
        Node<T> next = nodeAt(index);
        Node<T> prev = next.prev;
        Node<T> n = new Node<>(value);
        n.prev = prev; n.next = next;
        prev.next = n; next.prev = n;
        size++;
    }

    public T removeAt(int index) {
        Node<T> n = nodeAt(index);
        unlink(n);
        return n.value;
    }

    public T max() {
        ensureNotEmpty();
        T best = head.value;
        for (Node<T> c = head.next; c != null; c = c.next) {
            if (c.value.compareTo(best) > 0) best = c.value;
        }
        return best;
    }

    public T min() {
        ensureNotEmpty();
        T best = head.value;
        for (Node<T> c = head.next; c != null; c = c.next) {
            if (c.value.compareTo(best) < 0) best = c.value;
        }
        return best;
    }

    /** Додає множину елементів (масив/колекцію) у вказане місце списку */
    public void addAll(int index, Collection<? extends T> values) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        int i = index;
        for (T v : values) {
            insert(i++, v);
        }
    }
    public void addAll(int index, T[] arr) {
        addAll(index, Arrays.asList(arr));
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (Node<T> c = head; c != null; c = c.next, i++) {
            if (i > 0) sb.append(" ");
            sb.append("[index:").append(i).append("; value:").append(c.value).append("]");
        }
        sb.append("}");
        return sb.toString();
    }

    // --- допоміжні ---
    private void ensureNotEmpty() {
        if (size == 0) throw new NoSuchElementException("List is empty");
    }

    private Node<T> nodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index <= (size >>> 1)) {
            Node<T> c = head;
            for (int i = 0; i < index; i++) c = c.next;
            return c;
        } else {
            Node<T> c = tail;
            for (int i = size - 1; i > index; i--) c = c.prev;
            return c;
        }
    }

    private void unlink(Node<T> n) {
        Node<T> p = n.prev, nx = n.next;
        if (p != null) p.next = nx; else head = nx;
        if (nx != null) nx.prev = p; else tail = p;
        size--;
    }
}

// --- Демонстрація ---
class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addFirst(5);
        list.addLast(20);
        list.insert(1, 7);                 // [5, 7, 10, 20]
        list.addAll(2, new Integer[]{8,9}); // [5, 7, 8, 9, 10, 20]
        System.out.println("List: " + list);
        System.out.println("size = " + list.size());
        System.out.println("get(3) = " + list.get(3));
        System.out.println("contains(9) = " + list.contains(9));
        System.out.println("min = " + list.min() + ", max = " + list.max());
        System.out.println("removeAt(1) -> " + list.removeAt(1)); // remove 7
        System.out.println("removeFirst() -> " + list.removeFirst()); // remove 5
        System.out.println("removeLast() -> " + list.removeLast()); // remove 20
        System.out.println("After removals: " + list);
    }
}
