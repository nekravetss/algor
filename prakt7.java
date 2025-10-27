// Queue.java
public class Queue {
    private final int[] data;
    private int head;   // індекс першого елемента
    private int tail;   // індекс останнього елемента
    private int size;   // кількість елементів

    public Queue(int capacity) {
        data = new int[capacity];
        head = 0;
        tail = -1;
        size = 0;
    }

    // Додає елемент у кінець черги
    public void enqueue(int x) {
        if (size == data.length) {
            System.out.println("Черга переповнена!");
            return;
        }
        tail = (tail + 1) % data.length;
        data[tail] = x;
        size++;
    }

    // Видаляє і повертає елемент з початку черги
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Черга порожня!");
            return -1;
        }
        int x = data[head];
        head = (head + 1) % data.length;
        size--;
        return x;
    }

    // Повертає перший елемент без видалення
    public int peek() {
        if (isEmpty()) {
            System.out.println("Черга порожня!");
            return -1;
        }
        return data[head];
    }

    // Очищає чергу
    public void clear() {
        head = 0;
        tail = -1;
        size = 0;
    }

    // Кількість елементів
    public int count() { return size; }
    public int size()  { return size; }

    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        if (isEmpty()) return "Черга порожня";
        StringBuilder sb = new StringBuilder("Елементи черги: ");
        for (int i = 0; i < size; i++) {
            sb.append(data[(head + i) % data.length]).append(" ");
        }
        return sb.toString();
    }

    // Демонстрація
    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println(q);
        System.out.println("Перший: " + q.peek());
        System.out.println("Видалено: " + q.dequeue());
        System.out.println(q);
        System.out.println("К-сть: " + q.size());
        q.clear();
        System.out.println(q);
    }
}
