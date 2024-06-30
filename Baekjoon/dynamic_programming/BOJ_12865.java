package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[][] things = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            final String[] wv = br.readLine().split(" ");
            int w = Integer.parseInt(wv[0]);
            int v = Integer.parseInt(wv[1]);
            things[i][0] = w;
            things[i][1] = v;
        }
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j - things[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - things[i][0]] + things[i][1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
