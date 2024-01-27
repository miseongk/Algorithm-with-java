package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15649 {

    static int m;
    static int n;
    static boolean[] visited;
    static int[] arr;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        visited = new boolean[n];
        arr = new int[m];
        dfs(0);
    }

    private static void dfs(final int depth) {
        if (depth == m) {
            for (final int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
