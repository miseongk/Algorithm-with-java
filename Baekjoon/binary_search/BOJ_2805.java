package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2805 {

    static int[] trees;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int m = Integer.parseInt(nm[1]);
        final String[] treesString = br.readLine().split(" ");
        trees = Arrays.stream(treesString)
                .mapToInt(Integer::parseInt)
                .toArray();

        final int left = 0;
        final int right = Arrays.stream(trees)
                .max().getAsInt();
        final long length = binarySearch(left, right, m);
        System.out.println(length);
    }

    private static long binarySearch(final long left, final long right, final long target) {
        final long mid = (left + right) / 2;
        final long cutLength = cutTree(mid);
        if (cutLength == target || left > right) {
            return mid;
        } else if (cutLength > target) {
            return binarySearch(mid + 1, right, target);
        } else {
            return binarySearch(left, mid - 1, target);
        }
    }

    private static long cutTree(final long mid) {
        long sum = 0;
        for (int i = 0; i < trees.length; i++) {
            final long cut = trees[i] - mid;
            if (cut > 0) {
                sum += cut;
            }
        }

        return sum;
    }
}
