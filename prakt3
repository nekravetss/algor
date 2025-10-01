import java.util.*;

public class MatrixTasks {

    // Генерація матриці
    public static int[][] generateMatrix(int rows, int cols, int min, int max) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
        return matrix;
    }

    // Вивід
    public static void printMatrix(int[][] matrix) {
        System.out.print("       ");
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.printf("стовпець %d   ", j + 1);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("рядок %d  ", i + 1);
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6d   ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // 1. Зсув вправо і догори
    public static int[][] shiftMatrix(int[][] matrix, int kRight, int kUp) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] shifted = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int newI = (i - kUp + rows) % rows;
                int newJ = (j + kRight) % cols;
                shifted[newI][newJ] = matrix[i][j];
            }
        }
        return shifted;
    }

    // 2. Сума після 3-го елемента
    public static int[] sumAfterThird(int[][] matrix) {
        int[] sums = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 3; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            sums[i] = sum;
        }
        return sums;
    }

    // 3. Відняти середнє
    public static double[][] subtractRowAverage(int[][] matrix) {
        double[][] res = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            double avg = Arrays.stream(matrix[i]).average().orElse(0);
            for (int j = 0; j < matrix[i].length; j++) {
                res[i][j] = matrix[i][j] - avg;
            }
        }
        return res;
    }

    // 4. Видалити рядки/стовпці з максимумом
    public static int[][] deleteMaxRowsCols(int[][] matrix) {
        int max = Arrays.stream(matrix).flatMapToInt(Arrays::stream).max().orElse(0);
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == max) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        List<int[]> newMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (!rows.contains(i)) {
                int[] row = new int[matrix[i].length - cols.size()];
                int idx = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (!cols.contains(j)) {
                        row[idx++] = matrix[i][j];
                    }
                }
                newMatrix.add(row);
            }
        }
        return newMatrix.toArray(new int[0][]);
    }

    // 5. Swap стовпці з max і min
    public static int[][] swapMaxMinCols(int[][] matrix) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int colMax = 0, colMin = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    colMax = j;
                }
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    colMin = j;
                }
            }
        }

        int[][] res = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < res.length; i++) {
            int temp = res[i][colMax];
            res[i][colMax] = res[i][colMin];
            res[i][colMin] = temp;
        }
        return res;
    }

    // 6. Видалити рядок і стовпець з максимумом
    public static int[][] deleteRowColWithMax(int[][] matrix) {
        int max = Integer.MIN_VALUE, rowMax = 0, colMax = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    rowMax = i;
                    colMax = j;
                }
            }
        }

        List<int[]> newMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (i != rowMax) {
                int[] row = new int[matrix[i].length - 1];
                int idx = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (j != colMax) row[idx++] = matrix[i][j];
                }
                newMatrix.add(row);
            }
        }
        return newMatrix.toArray(new int[0][]);
    }

    // 7. Видалити всі рядки/стовпці з максимумом
    public static int[][] deleteAllMax(int[][] matrix) {
        return deleteMaxRowsCols(matrix);
    }

    // 8. Swap рядок і стовпець з max та min
    public static int[][] swapRowCol(int[][] matrix) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int rowMax = 0, colMax = 0, rowMin = 0, colMin = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    rowMax = i;
                    colMax = j;
                }
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    rowMin = i;
                    colMin = j;
                }
            }
        }

        int[][] res = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        int[] tmpRow = res[rowMax];
        res[rowMax] = res[rowMin];
        res[rowMin] = tmpRow;

        for (int i = 0; i < res.length; i++) {
            int tmp = res[i][colMax];
            res[i][colMax] = res[i][colMin];
            res[i][colMin] = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] M = generateMatrix(4, 5, -5, 9);
        System.out.println("Початкова матриця:");
        printMatrix(M);

        System.out.println("Зсув на 1 вправо і 1 догори:");
        printMatrix(shiftMatrix(M, 1, 1));

        System.out.println("Сума після 3-го елемента:");
        System.out.println(Arrays.toString(sumAfterThird(M)));

        System.out.println("Відняти середнє кожного рядка:");
        printMatrix(Arrays.stream(subtractRowAverage(M))
                          .map(row -> Arrays.stream(row).mapToInt(x -> (int)Math.round(x)).toArray())
                          .toArray(int[][]::new));

        System.out.println("Видалення рядків/стовпців з максимумом:");
        printMatrix(deleteMaxRowsCols(M));

        System.out.println("Swap стовпці з max і min:");
        printMatrix(swapMaxMinCols(M));

        System.out.println("Видалити рядок і стовпець з максимумом:");
        printMatrix(deleteRowColWithMax(M));

        System.out.println("Видалити всі рядки/стовпці з максимумами:");
        printMatrix(deleteAllMax(M));

        System.out.println("Swap рядок і стовпець з max і min:");
        printMatrix(swapRowCol(M));
    }
}
