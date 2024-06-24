package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        final long[] liquid = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        int s = 0;
        int e = n - 1;
        int minS = s;
        int minE = e;
        long minSum = liquid[minS] + liquid[minE];
        while (s < e) {
            final long sum = liquid[s] + liquid[e];
            if (Math.abs(sum) < Math.abs(minSum)) {
                minS = s;
                minE = e;
                minSum = sum;
            }
            if (sum == 0) {
                break;
            }
            if (sum > 0) {
                e--;
            } else {
                s++;
            }
        }
        System.out.println(liquid[minS] + " " + liquid[minE]);
    }
}
