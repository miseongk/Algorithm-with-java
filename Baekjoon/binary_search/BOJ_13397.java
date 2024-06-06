package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_13397 {

    static int n;
    static int arr[];

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int right = Arrays.stream(arr)
                .max()
                .getAsInt();
        int left = 0;

        while (left < right) {
            final int mid = (left + right) / 2;
            if (findMin(mid) <= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }

    private static int findMin(final int mid) {
        int cnt = 1;
        int min = (int) 1e9;
        int max = -1;
        for (int i = 0; i < n; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
            if (max - min > mid) {
                cnt++;
                min = (int) 1e9;
                max = -1;
                i--;
            }
        }
        return cnt;
    }
}
