package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15663 {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] nums;
    static int[] arr;
    static boolean[] visited;
    static boolean[][] numVisited;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        arr = new int[n];
        visited = new boolean[n];
        numVisited = new boolean[n][10001];
        dfs(0);
        System.out.print(sb);
    }

    private static void dfs(final int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || numVisited[depth][nums[i]]) {
                continue;
            }
            arr[depth] = nums[i];
            visited[i] = true;
            numVisited[depth][nums[i]] = true;
            dfs(depth + 1);
            visited[i] = false;
        }
        if (depth > 0) {
            Arrays.fill(numVisited[depth], false);
        }
    }
}
