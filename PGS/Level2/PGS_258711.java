package PGS.Level2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PGS_258711 {

    int vertexCnt;
    int edgeCnt;

    public int[] solution(final int[][] edges) {
        final int[] answer = new int[4];

        final List<List<Integer>> in = new ArrayList<>();
        final List<List<Integer>> out = new ArrayList<>();

        for (int i = 0; i <= 1000000; i++) {
            in.add(new ArrayList<>());
            out.add(new ArrayList<>());
        }
        final Set<Integer> canVertex = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            in.get(edges[i][1]).add(edges[i][0]);
            out.get(edges[i][0]).add(edges[i][1]);
            if (out.get(edges[i][0]).size() >= 2) {
                canVertex.add(edges[i][0]);
            }
        }
        for (final Integer v : canVertex) {
            if (in.get(v).size() == 0) {
                answer[0] = v;
                break;
            }
        }

        final boolean[] visited = new boolean[1000001];
        for (int i = 0; i < out.get(answer[0]).size(); i++) {
            vertexCnt = 0;
            edgeCnt = 0;
            dfs(out.get(answer[0]).get(i), out, visited);
            if (vertexCnt == edgeCnt) {
                answer[1]++;
            } else if (vertexCnt > edgeCnt) {
                answer[2]++;
            } else {
                answer[3]++;
            }
        }

        return answer;
    }

    private void dfs(final int v, final List<List<Integer>> out, final boolean[] visited) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        vertexCnt++;
        for (int i = 0; i < out.get(v).size(); i++) {
            edgeCnt++;
            dfs(out.get(v).get(i), out, visited);
        }
    }
}
