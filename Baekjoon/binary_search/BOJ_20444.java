package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20444 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nk = br.readLine().split(" ");
        final long n = Long.parseLong(nk[0]);
        final long k = Long.parseLong(nk[1]);

        if (n >= k) {
            System.out.println("NO");
            return;
        }
        long left = 0;
        long right = n;
        while (left <= right) {
            final long mid = (left + right) / 2;
            final long result = ((n - mid) + 1) * (mid + 1);
            if (result == k) {
                System.out.println("YES");
                return;
            }
            if (result > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("NO");
    }
}
