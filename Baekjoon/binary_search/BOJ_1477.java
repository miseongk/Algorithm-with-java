package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1477 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nml = br.readLine().split(" ");
        final int n = Integer.parseInt(nml[0]);
        final int m = Integer.parseInt(nml[1]);
        final int l = Integer.parseInt(nml[2]);
        int[] rest = new int[n];
        if (n != 0) {
            rest = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();
        }

        int left = 1;
        int right = l - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            final int cnt = findNewRest(mid, rest, l);
            if (cnt <= m) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right + 1);
    }

    private static int findNewRest(final int minDist, final int[] rest, final int l) {
        int cnt = 0;
        if (rest.length == 0) {
            cnt += (l - 1) / minDist;
        } else {
            cnt += (rest[0] - 1) / minDist;
            for (int i = 1; i < rest.length; i++) {
                cnt += (rest[i] - rest[i - 1] - 1) / minDist;
            }
            cnt += (l - rest[rest.length - 1] - 1) / minDist;
        }
        return cnt;
    }
}
