package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15655 {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] arr;
    static int[] nums;

    public static void main(final String[] args) throws IOException {
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        arr = new int[m];
        dfs(0, 0);
        System.out.print(sb);
    }

    private static void dfs(final int now, final int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = now; i < n; i++) {
            arr[depth] = nums[i];
            dfs(i + 1, depth + 1);
        }
    }
}
