// Stack.java
public class Stack {
    private int[] elements;
    private int top;
    private int capacity;

    public Stack(int size) {
        elements = new int[size];
        capacity = size;
        top = -1;
    }

    // Додає елемент у стек
    public void push(int item) {
        if (top == capacity - 1) {
            System.out.println("Стек переповнений");
            return;
        }
        elements[++top] = item;
    }

    // Видаляє і повертає верхній елемент
    public int pop() {
        if (isEmpty()) {
            System.out.println("Стек порожній");
            return -1;
        }
        return elements[top--];
    }

    // Повертає верхній елемент без видалення
    public int peek() {
        if (isEmpty()) {
            System.out.println("Стек порожній");
            return -1;
        }
        return elements[top];
    }

    // Очищає стек
    public void clear() {
        top = -1;
    }

    // Повертає кількість елементів
    public int count() {
        return top + 1;
    }

    // Те саме, що count()
    public int size() {
        return count();
    }

    // Виводить всі елементи
    public String toString() {
        if (isEmpty()) return "Стек порожній";
        StringBuilder sb = new StringBuilder("Елементи стеку: ");
        for (int i = top; i >= 0; i--) {
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.toString());
        System.out.println("Верхній елемент: " + stack.peek());
        System.out.println("Видалено: " + stack.pop());
        System.out.println(stack.toString());
        System.out.println("Кількість: " + stack.size());
        stack.clear();
        System.out.println(stack.toString());
    }
}
