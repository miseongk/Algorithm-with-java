package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_13023 {

    static List<List<Integer>> graph;
    static int flag = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            final String[] ab = br.readLine().split(" ");
            final int a = Integer.parseInt(ab[0]);
            final int b = Integer.parseInt(ab[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        final boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, 1, visited);
            if (flag == 1) {
                break;
            }
            visited[i] = false;
        }
        System.out.println(flag);
    }

    private static void dfs(final int start, final int depth, final boolean[] visited) {
        if (flag == 1) {
            return;
        }
        if (depth == 5) {
            flag = 1;
            return;
        }
        for (int i = 0; i < graph.get(start).size(); i++) {
            final Integer next = graph.get(start).get(i);
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, depth + 1, visited);
                visited[next] = false;
            }
        }
    }
}
