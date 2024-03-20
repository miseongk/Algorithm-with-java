package PGS.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PGS_118669 {

    List<List<int[]>> graph = new ArrayList<>();
    PriorityQueue<Node> result = new PriorityQueue<>();

    public int[] solution(final int n, final int[][] paths, final int[] gates, final int[] summits) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        final Set<Integer> gateSet = new HashSet<>();
        for (int i = 0; i < gates.length; i++) {
            gateSet.add(gates[i]);
        }
        final Set<Integer> summitSet = new HashSet<>();
        for (int i = 0; i < summits.length; i++) {
            summitSet.add(summits[i]);
        }
        for (int i = 0; i < paths.length; i++) {
            final int s = paths[i][0];
            final int e = paths[i][1];
            final int w = paths[i][2];
            graph.get(s).add(new int[]{e, w});
            graph.get(e).add(new int[]{s, w});
        }
        final int[] visited = new int[n + 1];
        Arrays.fill(visited, (int) 1e9);
        for (int i = 0; i < gates.length; i++) {
            dijkstra(gates[i], visited, gateSet, summitSet);
        }

        final Node answer = result.poll();
        return new int[]{answer.num, answer.weight};
    }

    private void dijkstra(final int start, final int[] visited, final Set<Integer> gates, final Set<Integer> summits) {
        final PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        visited[start] = 0;
        while (!pq.isEmpty()) {
            final Node now = pq.poll();
            final int num = now.num;
            final int dist = now.weight;
            if (visited[num] < dist) {
                continue;
            }
            for (int i = 0; i < graph.get(num).size(); i++) {
                final int[] next = graph.get(num).get(i);
                if (!gates.contains(next[0])) {
                    final int cost = Math.max(dist, next[1]);
                    if (visited[next[0]] > cost) {
                        visited[next[0]] = cost;
                        if (summits.contains(next[0])) {
                            result.add(new Node(next[0], visited[next[0]]));
                        } else {
                            pq.offer(new Node(next[0], cost));
                        }
                    }
                }
            }
        }
    }

    class Node implements Comparable<Node> {

        int num;
        int weight;

        Node(final int num, final int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(final Node other) {
            if (this.weight == other.weight) {
                return Integer.compare(this.num, other.num);
            }
            return Integer.compare(this.weight, other.weight);
        }
    }
}
