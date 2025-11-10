// Файл: Pr9.java
// Запуск: javac Pr9.java && java Pr9

import java.util.*;


public class Pr9 {
    static final int[] SIZES = {10_000, 100_000, 1_000_000, 10_000_000, 100_000_000};
    static final int RUNS = 3;
    static final int K = 6;
    static final int MINV = -10000, MAXV = 10000;

    static int[] makeRandomArray(int n, Random rnd) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(MAXV - MINV + 1) + MINV;
        }
        return a;
    }

    // Метод 1: повне сортування
    static int sixthBySort(int[] arr) {
        int[] b = Arrays.copyOf(arr, arr.length);
        Arrays.sort(b);
        return b[b.length - K];
    }

    // Метод 2: підтримка відсортованого топ-6 (спадно)
    static int sixthByTopK(int[] arr) {
        int[] top = new int[K];
        int len = 0;
        for (int v : arr) {
            if (len < K || v > top[len - 1]) {
                // вставка у відсортований (спадно) масив top[0..len)
                int i = Math.max(0, len - 1);
                while (i > 0 && top[i - 1] < v) i--;
                // зсув праворуч
                int end = Math.min(len, K - 1);
                for (int j = end; j > i; j--) top[j] = top[j - 1];
                top[i] = v;
                if (len < K) len++;
            }
        }
        return len == K ? top[K - 1] : Integer.MIN_VALUE;
    }

    // Метод 3: 6 разів знаходимо максимум і "витираємо"
    static int sixthByRepeatedMax(int[] arr) {
        int[] b = Arrays.copyOf(arr, arr.length);
        int sixth = Integer.MIN_VALUE;
        for (int t = 0; t < K; t++) {
            int maxVal = Integer.MIN_VALUE;
            int maxIdx = -1;
            for (int i = 0; i < b.length; i++) {
                if (b[i] > maxVal) { maxVal = b[i]; maxIdx = i; }
            }
            sixth = maxVal;
            if (maxIdx >= 0) b[maxIdx] = Integer.MIN_VALUE;
        }
        return sixth;
    }

    static class Stat {
        ArrayList<Double> times = new ArrayList<>();
        int lastVal;
        void add(double ms, int val) { times.add(ms); lastVal = val; }
        double avg() { return times.stream().mapToDouble(x->x).average().orElse(Double.NaN); }
        String pack() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(Locale.US, "%.2f ms  [", avg()));
            for (int i = 0; i < times.size(); i++) {
                if (i>0) sb.append(", ");
                sb.append(String.format(Locale.US, "%.0f", times.get(i)));
            }
            sb.append("]  ⇒ ").append(lastVal);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random(); // за бажанням можна фіксувати seed
        System.out.printf("%-14s | %-28s | %-28s | %-28s%n",
                "Розмір", "Спосіб 1 (сортування)", "Спосіб 2 (масив топ-6)", "Спосіб 3 (6 максимумів)");
        System.out.println("-----------------------------------------------------------------------------------------------"
                + "--------------------------------");

        for (int n : SIZES) {
            try {
                // Перевірка пам’яті: спроба виділити базовий масив
                int[] base = makeRandomArray(n, rnd);

                Stat s1 = new Stat(), s2 = new Stat(), s3 = new Stat();

                for (int r = 0; r < RUNS; r++) {
                    int[] a1 = Arrays.copyOf(base, base.length);
                    int[] a2 = Arrays.copyOf(base, base.length);
                    int[] a3 = Arrays.copyOf(base, base.length);

                    long t0 = System.nanoTime();
                    int v1 = sixthBySort(a1);
                    long t1 = System.nanoTime();
                    s1.add((t1 - t0) / 1e6, v1);

                    t0 = System.nanoTime();
                    int v2 = sixthByTopK(a2);
                    t1 = System.nanoTime();
                    s2.add((t1 - t0) / 1e6, v2);

                    t0 = System.nanoTime();
                    int v3 = sixthByRepeatedMax(a3);
                    t1 = System.nanoTime();
                    s3.add((t1 - t0) / 1e6, v3);
                }

                System.out.printf("%-14s | %-28s | %-28s | %-28s%n",
                        String.format("%,d", n), s1.pack(), s2.pack(), s3.pack());

            } catch (OutOfMemoryError oom) {
                System.out.printf("%-14s | %-28s | %-28s | %-28s%n",
                        String.format("%,d", n), "—", "—", "—");
                System.err.println("Пропущено " + String.format("%,d", n) + " через обмеження пам’яті. " +
                        "Запустіть з більшим heap, напр.: -Xmx2g, або зменште SIZES.");
            }
        }
    }
}
