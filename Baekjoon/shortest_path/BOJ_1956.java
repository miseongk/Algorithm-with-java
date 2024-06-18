package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1956 {

    static List<List<Node>> graph = new ArrayList<>();
    static int[] dist;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] ve = br.readLine().split(" ");
        final int v = Integer.parseInt(ve[0]);
        final int e = Integer.parseInt(ve[1]);
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            final String[] abc = br.readLine().split(" ");
            final int a = Integer.parseInt(abc[0]);
            final int b = Integer.parseInt(abc[1]);
            final int c = Integer.parseInt(abc[2]);
            graph.get(a).add(new Node(b, c));
        }
        int result = (int) 1e9;
        dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist, (int) 1e9);
            dijkstra(i);
            if (dist[i] == 0) {
                continue;
            }
            result = Math.min(dist[i], result);
        }
        if (result == (int) 1e9) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void dijkstra(final int start) {
        final PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            final Node now = pq.poll();
            if (now.num != start && dist[now.num] < now.dist) {
                continue;
            }
            for (int i = 0; i < graph.get(now.num).size(); i++) {
                final Node next = graph.get(now.num).get(i);
                final int cost = dist[now.num] + next.dist;
                if ((next.num == start && (dist[next.num] == 0 || cost < dist[next.num])) || cost < dist[next.num]) {
                    dist[next.num] = cost;
                    pq.add(new Node(next.num, cost));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        int num;
        int dist;

        public Node(final int num, final int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(final Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
