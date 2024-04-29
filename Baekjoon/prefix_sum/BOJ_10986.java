package Baekjoon.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10986 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        final long[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        final long[] sum = new long[arr.length + 1];
        final long[] cnt = new long[m];
        sum[0] = arr[0];
        cnt[(int) sum[0] % m]++;
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
            final long remain = sum[i] % m;
            cnt[(int) remain]++;
        }
        long result = nC2((int) cnt[0]) + cnt[0];
        for (int i = 1; i < m; i++) {
            result += nC2((int) cnt[i]);
        }
        System.out.println(result);
    }

    private static long nC2(final int n) {
        return (long) n * (n - 1) / 2;
    }
}
