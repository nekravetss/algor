public class RecursionTasks {

    // 1. Від 1 до n
    static void print1ToN(int n, int current) {
        if (current > n) return;
        System.out.print(current + " ");
        print1ToN(n, current + 1);
    }

    // 2. Від A до B
    static void printAtoB(int a, int b) {
        System.out.print(a + " ");
        if (a < b) printAtoB(a + 1, b);
        else if (a > b) printAtoB(a - 1, b);
    }

    // 3. Точна степінь двійки
    static boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n == 0 || n % 2 != 0) return false;
        return isPowerOfTwo(n / 2);
    }

    // 4. Сума цифр числа
    static int sumDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumDigits(n / 10);
    }

    // 5. Цифри числа справа наліво
    static void printDigitsReverse(int n) {
        if (n == 0) return;
        System.out.print(n % 10 + " ");
        printDigitsReverse(n / 10);
    }

    // 6. Цифри числа зліва направо
    static void printDigitsForward(int n) {
        if (n == 0) return;
        int rest = n / 10;
        if (rest > 0) printDigitsForward(rest);
        System.out.print(n % 10 + " ");
    }

    public static void main(String[] args) {
        System.out.println("1) Від 1 до n:");
        print1ToN(5, 1);

        System.out.println("\n\n2) Від A до B:");
        printAtoB(5, 1);

        System.out.println("\n\n3) Точна степінь двійки:");
        System.out.println("8 -> " + (isPowerOfTwo(8) ? "YES" : "NO"));
        System.out.println("3 -> " + (isPowerOfTwo(3) ? "YES" : "NO"));

        System.out.println("\n4) Сума цифр числа:");
        System.out.println("179 -> " + sumDigits(179));

        System.out.println("\n5) Цифри справа наліво:");
        printDigitsReverse(179);

        System.out.println("\n\n6) Цифри зліва направо:");
        printDigitsForward(179);
    }
}
