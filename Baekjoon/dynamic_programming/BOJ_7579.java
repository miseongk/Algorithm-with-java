package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_7579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        final String[] aArr = br.readLine().split(" ");
        final String[] mArr = br.readLine().split(" ");
        int[][] app = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            app[i][0] = Integer.parseInt(aArr[i - 1]); // 메모리
            app[i][1] = Integer.parseInt(mArr[i - 1]); // 비용
        }
        long[] dp = new long[m + 1];
        Arrays.fill(dp, (int) 1e9);
        for (int i = 1; i <= n; i++) {
            for (int j = m; j > 0; j--) {
                if (j - app[i][0] > 0) {
                    dp[j] = Math.min(dp[j], dp[j - app[i][0]] + app[i][1]);
                } else {
                    dp[j] = Math.min(app[i][1], dp[j]);
                }
            }
        }
        System.out.println(dp[m]);
    }
}
