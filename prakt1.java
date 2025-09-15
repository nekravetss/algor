// Практична робота №1

import java.util.*;

public class ArraysLab {

    // 1. Порахувати кількість та суму парних елементів у діапазоні
    public static Map<String, Integer> evenCountAndSum(int[] arr, int min, int max) {
        int count = 0, sum = 0;
        for (int v : arr) {
            if (v % 2 == 0 && v >= min && v <= max) {
                count++;
                sum += v;
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("count", count);
        result.put("sum", sum);
        return result;
    }

    // 2. Середнє та кількість більших за нього
    public static Map<String, Double> avgAndGreater(int[] arr) {
        double avg = Arrays.stream(arr).average().orElse(0);
        long count = Arrays.stream(arr).filter(v -> v > avg).count();

        Map<String, Double> result = new HashMap<>();
        result.put("avg", avg);
        result.put("count", (double) count);
        return result;
    }

    // 3. Попарна сума масивів
    public static int[] pairwiseSum(int[] arr1, int[] arr2) {
        int len = Math.min(arr1.length, arr2.length);
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }

    // 4. Конкатенація
    public static int[] concatArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    // 5. Поміняти місцями max і min
    public static int[] swapMinMax(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        int maxIndex = 0, minIndex = 0;
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] > copy[maxIndex]) maxIndex = i;
            if (copy[i] < copy[minIndex]) minIndex = i;
        }
        int temp = copy[maxIndex];
        copy[maxIndex] = copy[minIndex];
        copy[minIndex] = temp;
        return copy;
    }

    // 6. Поділ на додатні та від’ємні
    public static Map<String, List<Integer>> splitPositiveNegative(int[] arr) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int v : arr) {
            if (v > 0) positive.add(v);
            if (v < 0) negative.add(v);
        }
        Map<String, List<Integer>> result = new HashMap<>();
        result.put("positive", positive);
        result.put("negative", negative);
        return result;
    }

    // 7. Видалити дублікати max і min
    public static int[] removeDuplicatesMinMax(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);
        boolean seenMax = false, seenMin = false;

        List<Integer> result = new ArrayList<>();
        for (int v : arr) {
            if (v == max) {
                if (seenMax) continue;
                seenMax = true;
            }
            if (v == min) {
                if (seenMin) continue;
                seenMin = true;
            }
            result.add(v);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 8. Середні двох масивів і елементи між ними
    public static Map<String, Object> thirdArrayBetweenAverages(int[] arr1, int[] arr2) {
        double avg1 = Arrays.stream(arr1).average().orElse(0);
        double avg2 = Arrays.stream(arr2).average().orElse(0);

        double minAvg = Math.min(avg1, avg2);
        double maxAvg = Math.max(avg1, avg2);

        List<Integer> combined = new ArrayList<>();
        for (int v : arr1) combined.add(v);
        for (int v : arr2) combined.add(v);

        List<Integer> result = new ArrayList<>();
        for (int v : combined) {
            if (v >= minAvg && v <= maxAvg) result.add(v);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("avg1", avg1);
        map.put("avg2", avg2);
        map.put("result", result);
        return map;
    }

    // Метод для друку масиву у потрібному форматі
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("[елемент_%d_значення_%d]\n", i + 1, arr[i]);
        }
    }

    // ----------------------- ТЕСТ -----------------------
    public static void main(String[] args) {
        int[] array1 = {5, -2, 10, 7, -8, 3};
        int[] array2 = {4, 6, -1, 9, -3, 12};

        System.out.println("Масив 1:");
        printArray(array1);
        System.out.println("Масив 2:");
        printArray(array2);

        System.out.println("\n1. " + evenCountAndSum(array1, -10, 20));
        System.out.println("2. " + avgAndGreater(array1));
        System.out.println("3. " + Arrays.toString(pairwiseSum(array1, array2)));
        System.out.println("4. " + Arrays.toString(concatArrays(array1, array2)));
        System.out.println("5. " + Arrays.toString(swapMinMax(array1)));
        System.out.println("6. " + splitPositiveNegative(array1));
        System.out.println("7. " + Arrays.toString(removeDuplicatesMinMax(array1)));
        System.out.println("8. " + thirdArrayBetweenAverages(array1, array2));
    }
}
