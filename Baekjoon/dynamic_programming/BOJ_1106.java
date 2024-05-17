package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1106 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] cn = br.readLine().split(" ");
        final int c = Integer.parseInt(cn[0]);
        final int n = Integer.parseInt(cn[1]);
        final int[] dp = new int[1101];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        final int[][] city = new int[n][2];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            final int cost = Integer.parseInt(input[0]);
            final int cnt = Integer.parseInt(input[1]);
            city[i][0] = cost;
            city[i][1] = cnt;
        }
        for (int i = 0; i < n; i++) {
            for (int j = city[i][1]; j <= c + 100; j++) {
                dp[j] = Math.min(dp[j - city[i][1]] + city[i][0], dp[j]);
            }
        }
        
        int result = 100000;
        for (int i = c; i <= c + 100; i++) {
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }
}
