package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2470 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final long[] liquid = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(liquid);

        int start = 0;
        int end = n - 1;
        long min = (long) 2e9;
        final long[] result = new long[2];
        while (start < end) {
            final long sum = liquid[start] + liquid[end];
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                result[0] = liquid[start];
                result[1] = liquid[end];

                if (sum == 0) {
                    break;
                }
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}
