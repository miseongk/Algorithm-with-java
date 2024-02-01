package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2559 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nk = br.readLine().split(" ");
        final int n = Integer.parseInt(nk[0]);
        final int k = Integer.parseInt(nk[1]);
        final int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int max = 0;
        for (int i = 0; i < k; i++) {
            max += arr[i];
        }
        int start = 0;
        int end = k;
        int prev = max;
        while (end < n) {
            final int nextAdd = arr[end] - arr[start];
            final int next = prev + nextAdd;
            if (next > max) {
                max = next;
            }
            prev += nextAdd;
            start++;
            end++;
        }
        System.out.println(max);
    }
}
