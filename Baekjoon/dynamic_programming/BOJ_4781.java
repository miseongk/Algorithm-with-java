package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4781 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            final String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = (int) (Double.parseDouble(nm[1]) * 100 + 0.5);
            if (n == 0 && m == 0.00) {
                break;
            }
            long[] dp = new long[m + 1];
            int[][] candy = new int[n][2];
            for (int i = 0; i < n; i++) {
                final String[] cp = br.readLine().split(" ");
                int c = Integer.parseInt(cp[0]);
                int p = (int) (Double.parseDouble(cp[1]) * 100 + 0.5);
                candy[i][0] = c;
                candy[i][1] = p;
            }
            for (int j = 0; j < n; j++) {
                for (int i = 1; i <= m; i++) {
                    if (i - candy[j][1] >= 0) {
                        dp[i] = Math.max(dp[i], dp[i - candy[j][1]] + candy[j][0]);
                    } else {
                        dp[i] = Math.max(dp[i - 1], dp[i]);
                    }
                }
            }
            sb.append(dp[m]).append("\n");
        }
        System.out.print(sb);
    }
}
