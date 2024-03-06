package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ_2668 {

    static int n;
    static int[] arr;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        final Set<Integer> canNum = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            canNum.add(arr[i]);
        }
        final Set<Integer> result = new HashSet<>();
        for (final Integer num : canNum) {
            if (result.contains(num)) {
                continue;
            }
            final Set<Integer> set = dfs(num);
            result.addAll(set);
        }

        System.out.println(result.size());
        final List<Integer> resultList = new ArrayList<>(result);
        resultList.sort(Comparator.naturalOrder());
        for (final Integer integer : resultList) {
            System.out.println(integer);
        }
    }

    private static Set<Integer> dfs(int num) {
        final Set<Integer> upTmp = new HashSet<>();
        final Set<Integer> downTmp = new HashSet<>();
        final boolean[] visited = new boolean[n + 1];
        while (!visited[num]) {
            upTmp.add(num);
            downTmp.add(arr[num]);
            visited[num] = true;
            num = arr[num];
        }

        if (upTmp.containsAll(downTmp) && upTmp.size() == downTmp.size()) {
            return upTmp;
        }
        return Collections.emptySet();
    }
}
