package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_10159 {

    static int n;
    static List<List<Integer>> inGraph = new ArrayList<>();
    static List<List<Integer>> outGraph = new ArrayList<>();
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            inGraph.add(new ArrayList<>());
            outGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            final String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            outGraph.get(a).add(b);
            inGraph.get(b).add(a);
        }
        for (int i = 1; i <= n; i++) {
            cnt = 0;
            visited = new boolean[n + 1];
            dfs(i, inGraph);
            dfs(i, outGraph);
            sb.append(n - cnt - 1).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int now, List<List<Integer>> graph) {
        for (int i = 0; i < graph.get(now).size(); i++) {
            final Integer next = graph.get(now).get(i);
            if (visited[next]) {
                continue;
            }
            cnt++;
            visited[next] = true;
            dfs(next, graph);
        }
    }
}
