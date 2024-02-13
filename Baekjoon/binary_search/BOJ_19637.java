package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_19637 {

    static long[] upperLimit;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        final String[] title = new String[n];
        upperLimit = new long[n];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            title[i] = input[0];
            upperLimit[i] = Long.parseLong(input[1]);
        }
        for (int i = 0; i < m; i++) {
            final long target = Long.parseLong(br.readLine());
            final int titleIdx = binarySearch(target, 0, n - 1);
            sb.append(title[titleIdx]).append("\n");
        }
        System.out.print(sb);
    }

    private static int binarySearch(final long target, final int start, final int end) {
        if (start > end) {
            return start;
        }

        final int midIdx = (start + end) / 2;
        if (target > upperLimit[midIdx]) {
            return binarySearch(target, midIdx + 1, end);
        } else {
            return binarySearch(target, start, midIdx - 1);
        }
    }
}
