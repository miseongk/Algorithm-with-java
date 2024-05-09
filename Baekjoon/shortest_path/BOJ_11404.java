package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11404 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int m = Integer.parseInt(br.readLine());
        final int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            final String[] abc = br.readLine().split(" ");
            final int a = Integer.parseInt(abc[0]);
            final int b = Integer.parseInt(abc[1]);
            final int c = Integer.parseInt(abc[2]);
            if (dist[a][b] > c) {
                dist[a][b] = c;
            }
        }
        floyd(dist, n);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == (int) 1e9) {
                    System.out.print("0 ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void floyd(final int[][] dist, final int n) {
        // 경유지
        for (int k = 1; k <= n; k++) {
            // 출발지
            for (int i = 1; i <= n; i++) {
                // 도착지
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
