package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1504 {

    static final int INF = (int) 1e9;

    static List<List<Node>> graph = new ArrayList<>();
    static long[] distance;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] ne = br.readLine().split(" ");
        final int n = Integer.parseInt(ne[0]);
        final int e = Integer.parseInt(ne[1]);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            final String[] abc = br.readLine().split(" ");
            final int a = Integer.parseInt(abc[0]);
            final int b = Integer.parseInt(abc[1]);
            final int c = Integer.parseInt(abc[2]);
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        final String[] uv = br.readLine().split(" ");
        final int u = Integer.parseInt(uv[0]);
        final int v = Integer.parseInt(uv[1]);

        long result1 = 0;
        long result2 = 0;
        dijkstra(1, n);
        result1 += distance[u];
        result2 += distance[v];
        dijkstra(u, n);
        result1 += distance[v];
        result2 += distance[v];
        dijkstra(n, n);
        result1 += distance[v];
        result2 += distance[u];

        final long result = Math.min(result1, result2);
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void dijkstra(final int start, final int vertexNum) {
        distance = new long[vertexNum + 1];
        for (int i = 0; i <= vertexNum; i++) {
            Arrays.fill(distance, INF);
        }
        final PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            final Node node = pq.poll();
            final int now = node.getIndex();
            final int dist = node.getDistance();
            if (dist < distance[now]) {
                continue;
            }
            for (int i = 0; i < graph.get(now).size(); i++) {
                final int cost = graph.get(now).get(i).getDistance() + dist;
                if (cost < distance[graph.get(now).get(i).getIndex()]) {
                    distance[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {

    private final int index;
    private final int distance;

    public Node(final int index, final int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(final Node o) {
        if (this.distance < o.distance) {
            return -1;
        }
        return 1;
    }
}
