package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11663 {

    static long[] dots;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        dots = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();
        for (int i = 0; i < m; i++) {
            final String[] se = br.readLine().split(" ");
            final long start = Long.parseLong(se[0]);
            final long end = Long.parseLong(se[1]);
            final int startIdx = binarySearch(0, n - 1, start, true);
            final int endIdx = binarySearch(startIdx, n - 1, end, false);
            sb.append(endIdx - startIdx + 1).append("\n");
        }
        System.out.print(sb);
    }

    private static int binarySearch(final int start, final int end, final long target, final boolean isStart) {
        final int mid = (start + end) / 2;
        if (target == dots[mid]) {
            return mid;
        }
        if (start > end) {
            if (isStart) {
                return start;
            } else {
                return end;
            }
        }
        if (target > dots[mid]) {
            return binarySearch(mid + 1, end, target, isStart);
        } else {
            return binarySearch(start, mid - 1, target, isStart);
        }
    }
}
