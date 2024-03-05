package Baekjoon.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_21318 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int n = Integer.parseInt(br.readLine());
        final long[] difficulty = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        final long[] sum = new long[n];
        for (int i = 1; i < n; i++) {
            int add = 0;
            if (difficulty[i - 1] > difficulty[i]) {
                add++;
            }
            sum[i] = sum[i - 1] + add;
        }
        final int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            final String[] xy = br.readLine().split(" ");
            final int x = Integer.parseInt(xy[0]);
            final int y = Integer.parseInt(xy[1]);
            if (x == y) {
                sb.append(0).append("\n");
                continue;
            }
            sb.append(sum[y - 1] - sum[x - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
