package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15652 {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] arr;

    public static void main(final String[] args) throws IOException {
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        arr = new int[m];
        for (int i = 1; i <= n; i++) {
            dfs(i, 0);
        }
        System.out.println(sb);
    }

    private static void dfs(final int now, final int depth) {
        arr[depth] = now;
        if (depth == m - 1) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = now; i <= n; i++) {
            dfs(i, depth + 1);
        }
    }
}
