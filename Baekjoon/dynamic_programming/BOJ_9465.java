package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9465 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final int n = Integer.parseInt(br.readLine());
            final long[][] stickers = new long[2][n];
            for (int j = 0; j < 2; j++) {
                final String[] inputs = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    stickers[j][k] = Long.parseLong(inputs[k]);
                }
            }
            final long[][] dp = new long[2][n];
            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
            for (int j = 1; j < n; j++) {
                dp[0][j] = Math.max(dp[1][j - 1] + stickers[0][j], dp[0][j]);
                dp[1][j] = Math.max(dp[0][j - 1] + stickers[1][j], dp[1][j]);
                if (j < n - 1) {
                    dp[0][j + 1] = dp[1][j - 1] + stickers[0][j + 1];
                    dp[1][j + 1] = dp[0][j - 1] + stickers[1][j + 1];
                }
            }
            /*
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
             */
            sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append("\n");
        }
        System.out.print(sb);
    }
}
