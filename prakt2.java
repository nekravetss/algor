import java.util.Random;

public class SortingDemo {

    // Метод генерації масиву заданої довжини в діапазоні [min, max]
    public static int[] generateArray(int length, int min, int max) {
        Random rand = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    // Метод для виводу масиву у форматі
    public static void printArray(int[] arr) {
        System.out.println("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("  [cell - " + i + ", value - " + arr[i] + "]");
        }
        System.out.println("}");
    }

    // Bubble Sort
    public static int[] bubbleSort(int[] arr, boolean ascending) {
        int[] sorted = arr.clone();
        int n = sorted.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((ascending && sorted[j] > sorted[j + 1]) ||
                    (!ascending && sorted[j] < sorted[j + 1])) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    // Insertion Sort
    public static int[] insertionSort(int[] arr, boolean ascending) {
        int[] sorted = arr.clone();
        for (int i = 1; i < sorted.length; i++) {
            int key = sorted[i];
            int j = i - 1;
            while (j >= 0 && ((ascending && sorted[j] > key) || (!ascending && sorted[j] < key))) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    // Selection Sort
    public static int[] selectionSort(int[] arr, boolean ascending) {
        int[] sorted = arr.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            int target = i;
            for (int j = i + 1; j < sorted.length; j++) {
                if ((ascending && sorted[j] < sorted[target]) ||
                    (!ascending && sorted[j] > sorted[target])) {
                    target = j;
                }
            }
            int temp = sorted[i];
            sorted[i] = sorted[target];
            sorted[target] = temp;
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = generateArray(10, 1, 20);

        System.out.println("Початковий масив:");
        printArray(arr);

        System.out.println("\nBubble Sort (зростання):");
        printArray(bubbleSort(arr, true));

        System.out.println("\nBubble Sort (спадання):");
        printArray(bubbleSort(arr, false));

        System.out.println("\nInsertion Sort (зростання):");
        printArray(insertionSort(arr, true));

        System.out.println("\nInsertion Sort (спадання):");
        printArray(insertionSort(arr, false));

        System.out.println("\nSelection Sort (зростання):");
        printArray(selectionSort(arr, true));

        System.out.println("\nSelection Sort (спадання):");
        printArray(selectionSort(arr, false));
    }
}
