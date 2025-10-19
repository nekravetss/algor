import java.util.Arrays;

public class QuickSortExample {

    /**
     * Головний метод, що сортує масив за допомогою Quick Sort.
     * Приймає масив та булеве значення для напрямку сортування.
     * 
     * @param arr Масив цілих чисел[cite: 2].
     * @param ascending true для сортування за зростанням, false - за спаданням.
     */
    public static void sortArrayQuickSort(int[] arr, boolean ascending) {
        quickSortRecursive(arr, 0, arr.length - 1, ascending);
    }

    /**
     * Рекурсивна функція реалізації Quick Sort.
     * 
     */
    private static void quickSortRecursive(int[] arr, int low, int high, boolean ascending) {
        // Базовий випадок: поки в підмасиві більше одного елемента 
        if (low < high) {
            // pi - індекс опорного елемента після розділення
            int pi = partition(arr, low, high, ascending);

            // Рекурсивний виклик для підмасивів
            quickSortRecursive(arr, low, pi - 1, ascending); // Елементи зліва 
            quickSortRecursive(arr, pi + 1, high, ascending); // Елементи справа 
        }
    }

    /**
     * Функція розділення (Partition).
     * Обирає останній елемент як опору (pivot) [cite: 37] та розміщує його
     * на правильній позиції у відсортованому масиві.
     * 
     */
    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high]; // Опорний елемент [cite: 37]
        int i = (low - 1); // Індекс меншого/більшого елемента

        for (int j = low; j < high; j++) {
            boolean condition;
            // Умова для сортування за зростанням
            if (ascending) {
                condition = (arr[j] <= pivot);
            } 
            // Умова для сортування за спаданням
            else {
                condition = (arr[j] >= pivot);
            }

            if (condition) {
                i++;
                swap(arr, i, j); // Обмін елементів [cite: 39]
            }
        }

        // Розміщуємо опорний елемент на його кінцевій позиції
        swap(arr, i + 1, high);
        return (i + 1); // Повертаємо індекс опори
    }

    /**
     * Допоміжна функція для обміну двох елементів у масиві.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // --- Приклад виконання ---
    public static void main(String[] args) {
        int[] numbers = {9, -3, 5, 2, 6, 8, -6, 1, 3}; // Масив з Рис. 1 [cite: 6, 7, 8, 9, 10, 11]
        System.out.println("Оригінальний масив: " + Arrays.toString(numbers));

        // Сортування за зростанням [cite: 2]
        // Копіюємо масив, щоб не змінювати оригінал
        int[] numbersAsc = Arrays.copyOf(numbers, numbers.length);
        sortArrayQuickSort(numbersAsc, true);
        System.out.println("Відсортовано (за зростанням): " + Arrays.toString(numbersAsc));

        // Сортування за спаданням [cite: 2]
        int[] numbersDesc = Arrays.copyOf(numbers, numbers.length);
        sortArrayQuickSort(numbersDesc, false);
        System.out.println("Відсортовано (за спаданням): " + Arrays.toString(numbersDesc));
    }
}
