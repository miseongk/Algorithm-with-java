package Baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 징검다리 건너기 (large)
public class BOJ_22871 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final String[] aArr = br.readLine().split(" ");
        final int[] a = Arrays.stream(aArr)
                .mapToInt(Integer::parseInt)
                .toArray();
        final long[] dp = new long[n];
        dp[0] = 0;
        dp[1] = Math.abs(a[1] - a[0]) + 1;
        for (int i = 2; i < n; i++) {
            dp[i] = (long) i * (Math.abs(a[i] - a[0]) + 1);
            for (int j = 1; j < i; j++) {
                final long tmp = Math.max((long) (i - j) * (Math.abs(a[i] - a[j]) + 1), dp[j]);
                dp[i] = Math.min(tmp, dp[i]);
            }
        }
        System.out.println(dp[n - 1]);
    }
}
