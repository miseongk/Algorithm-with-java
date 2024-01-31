package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15650 {

    static int m;
    static int n;
    static int[] arr;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        arr = new int[m];
        dfs(0, 1);
    }

    private static void dfs(final int depth, final int next) {
        if (depth == m) {
            for (final int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }
        for (int i = next; i <= n; i++) {
            arr[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
}
