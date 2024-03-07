package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15664 {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] nums;
    static int[] arr;
    static boolean[] visited;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        arr = new int[m];
        visited = new boolean[n];
        dfs(0, 0);
        System.out.print(sb);
    }

    private static void dfs(final int j, final int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        int before = 0;
        for (int i = j; i < n; i++) {
            if (!visited[i] && before != nums[i]) {
                arr[depth] = nums[i];
                visited[i] = true;
                before = nums[i];
                dfs(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
}
