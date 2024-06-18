package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1956_2 {

    static int[][] matrix;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] ve = br.readLine().split(" ");
        final int v = Integer.parseInt(ve[0]);
        final int e = Integer.parseInt(ve[1]);
        matrix = new int[v + 1][v + 1];
        for (int i = 0; i <= v; i++) {
            Arrays.fill(matrix[i], (int) 1e9);
        }
        for (int i = 0; i < e; i++) {
            final String[] abc = br.readLine().split(" ");
            final int a = Integer.parseInt(abc[0]);
            final int b = Integer.parseInt(abc[1]);
            final int c = Integer.parseInt(abc[2]);
            matrix[a][b] = c;
        }
        floyd(v);
        int result = (int) 1e9;
        for (int i = 1; i <= v; i++) {
            result = Math.min(matrix[i][i], result);
        }
        if (result == (int) 1e9) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void floyd(final int v) {
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }
}
