package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2110 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nc = br.readLine().split(" ");
        final int n = Integer.parseInt(nc[0]);
        final int c = Integer.parseInt(nc[1]);
        long[] house = new long[n];
        for (int i = 0; i < n; i++) {
            house[i] = Long.parseLong(br.readLine());
        }
        house = Arrays.stream(house)
                .sorted()
                .toArray();

        long left = 0;
        long right = house[n - 1] - house[0];
        long mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            final int cnt = findInstallCnt(mid, house);
            if (cnt < c) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }

    private static int findInstallCnt(final long minDist, final long[] house) {
        int cnt = 1;
        long prev = house[0];
        for (int i = 1; i < house.length; i++) {
            if (house[i] >= prev + minDist) {
                cnt++;
                prev = house[i];
            }
        }
        return cnt;
    }
}
