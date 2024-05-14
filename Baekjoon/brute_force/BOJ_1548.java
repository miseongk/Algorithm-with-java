package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1548 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] b = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int result = 1;
        if (n < 3) {
            result = n;
        }
        for (int i = 0; i < n - 2; i++) {
            int cnt = 2;
            final int b1 = b[i];
            final int b2 = b[i + 1];
            int j = i;
            while (j < n - 2 && b1 + b2 > b[j + 2]) {
                cnt++;
                j++;
            }
            result = Math.max(cnt, result);
        }
        System.out.println(result);
    }
}
