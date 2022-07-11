package life;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static int n = scanner.nextInt(); //size of universe
    public static int m = 13; //generations
    public static String[][] first = new String[n][n];
    public static String[][] next = new String[n][n];

    public static void main(String[] args) throws InterruptedException, IOException {
        firstGeneration();
        for (int i = 0; i < m; i++) {
            GenerationAlgorithm.neighbours(first, next, n);
            int a = alive();
            int t = i + 1;
            System.out.println("Generation #" + t);
            System.out.println("Alive: " + a);
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(first[u][j]);
                }
                System.out.println();
            }
            Thread.sleep(1000);
        }
    }


    private static int alive() {
        int alive = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (first[i][j].equals("O")) {
                    alive++;
                }
            }
        }
        return alive;
    }

    private static void firstGeneration() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (random.nextBoolean() == true) {
                    first[i][j] = "O";
                } else {
                    first[i][j] = " ";
                }
            }
        }
    }

    public static void v1() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int seed = scanner.nextInt();
        Random random = new Random(seed);
        String[][] field = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean() == true) {
                    field[i][j] = "O";
                } else {
                    field[i][j] = " ";
                }
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
