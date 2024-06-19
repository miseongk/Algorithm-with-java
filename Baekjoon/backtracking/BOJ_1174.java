package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1174 {

    static List<Long> list = new ArrayList<>();
    static int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());

        dfs(0, 0);
        Collections.sort(list);
        if (n > list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n - 1));
        }
    }

    private static void dfs(final long num, final int idx) {
        if (!list.contains(num)) {
            list.add(num);
        }
        if (idx >= 10) {
            return;
        }

        dfs((num * 10) + arr[idx], idx + 1);
        dfs(num, idx + 1);
    }
}
