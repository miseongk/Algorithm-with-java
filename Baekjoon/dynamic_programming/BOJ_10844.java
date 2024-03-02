package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10844 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final long[][] dp = new long[100][10]; // 자릿수, 0~9로 시작하는 숫자 개수
        dp[0][0] = 0;
        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j + 1] = (dp[i][j + 1] + dp[i - 1][j]) % 1000000000;
                } else if (j == 9) {
                    dp[i][j - 1] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
                } else {
                    dp[i][j - 1] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
                    dp[i][j + 1] = (dp[i][j + 1] + dp[i - 1][j]) % 1000000000;
                }
            }
        }
        final long sum = Arrays.stream(dp[n - 1]).sum() % 1000000000;
        System.out.println(sum);
    }
}
