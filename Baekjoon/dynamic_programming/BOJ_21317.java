package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_21317 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[][] jump = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            final String[] sb = br.readLine().split(" ");
            jump[i][0] = Integer.parseInt(sb[0]);
            jump[i][1] = Integer.parseInt(sb[1]);
        }
        final int k = Integer.parseInt(br.readLine());

        final int[][] dp = new int[n + 2][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], (int) 1e9);
        }
        dp[0][0] = 0;
        for (int i = 0; i < n - 1; i++) {
            dp[i + 1][0] = Math.min(dp[i][0] + jump[i][0], dp[i + 1][0]);
            dp[i + 2][0] = Math.min(dp[i][0] + jump[i][1], dp[i + 2][0]);
            dp[i + 3][1] = Math.min(dp[i][0] + k, dp[i + 3][0]);
            if (dp[i][1] != (int) 1e9) {
                dp[i + 1][1] = Math.min(dp[i][1] + jump[i][0], dp[i + 1][1]);
                dp[i + 2][1] = Math.min(dp[i][1] + jump[i][1], dp[i + 2][1]);
            }
        }
        System.out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
    }
}
