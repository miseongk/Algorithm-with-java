package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_22945 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] ability = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = 0;
        int s = 0;
        int e = n - 1;
        while (s < e) {
            final int min = Math.min(ability[s], ability[e]);
            final int dist = e - s - 1;
            result = Math.max(min * dist, result);
            if (ability[s] <= ability[e]) {
                s++;
            } else {
                e--;
            }
        }
        System.out.println(result);
    }
}
