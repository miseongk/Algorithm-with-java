package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_20366 {

    static int n;
    static long[] snow;
    static boolean[] visited;
    static int[] arr;
    static long result;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        snow = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();
        visited = new boolean[n];
        arr = new int[n];
        result = snow[n - 1] + snow[n - 2];
        nC2(0, 0);
        System.out.println(result);
    }

    // 굳이 조합으로 안하고 2중 for문으로 해도 됨
    private static void nC2(final int depth, final int idx) {
        if (depth == 2) {
            result = Math.min(findMinSnowman(), result);
            return;
        }
        for (int i = idx; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            arr[depth] = i;
            visited[i] = true;
            nC2(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static long findMinSnowman() {
        final long firstSnowman = snow[arr[0]] + snow[arr[1]];
        int s = 0;
        int e = n - 1;
        long minDiff = snow[n - 1] + snow[n - 2];
        while (s < e) {
            while (s == arr[0] || s == arr[1]) {
                s++;
            }
            while (e == arr[1] || e == arr[0]) {
                e--;
            }
            if (s >= e) {
                break;
            }
            final long secondSnowman = snow[s] + snow[e];
            final long diff = Math.abs(firstSnowman - secondSnowman);
            minDiff = Math.min(diff, minDiff);
            if (firstSnowman < secondSnowman) {
                e--;
            } else if (firstSnowman > secondSnowman) {
                s++;
            } else {
                break;
            }
        }
        return minDiff;
    }
}
