package Baekjoon.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11660 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        final int[][] arr = new int[n + 1][n + 1];
        final long[][] sArr = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(input[j - 1]);
                sArr[i][j] = arr[i][j] + sArr[i - 1][j] + sArr[i][j - 1] - sArr[i - 1][j - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            final String[] xy = br.readLine().split(" ");
            final int x1 = Integer.parseInt(xy[0]);
            final int y1 = Integer.parseInt(xy[1]);
            final int x2 = Integer.parseInt(xy[2]);
            final int y2 = Integer.parseInt(xy[3]);
            final long s = sArr[x2][y2] - sArr[x2][y1 - 1] - sArr[x1 - 1][y2] + sArr[x1 - 1][y1 - 1];
            sb.append(s).append(" \n");
        }
        System.out.print(sb);
    }
}
