package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1277 {

    static int n;
    static int[][] plants;
    static Double[][] graph;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        final int w = Integer.parseInt(st.nextToken());
        plants = new int[n][n];
        graph = new Double[n][n];
        final double m = Double.parseDouble(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            plants[i][0] = Integer.parseInt(st.nextToken());
            plants[i][1] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                final double diff = getDiff(plants[i], plants[j]);
                if (diff <= m) {
                    graph[i][j] = diff;
                    graph[j][i] = diff;
                }
            }
        }
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            final int one = Integer.parseInt(st.nextToken()) - 1;
            final int two = Integer.parseInt(st.nextToken()) - 1;
            graph[one][two] = 0.0;
            graph[two][one] = 0.0;
        }

        final double result = dijkstra();
        System.out.println((int) Math.floor(result * 1000));
    }

    public static double getDiff(final int[] p1, final int[] p2) {
        return Math.pow(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2), 0.5);
    }

    private static double dijkstra() {
        final PriorityQueue<Node> pq = new PriorityQueue<>();
        final double[] visited = new double[n];
        Arrays.fill(visited, Double.MAX_VALUE);
        pq.add(new Node(0, 0));
        visited[0] = 0;
        while (!pq.isEmpty()) {
            final Node now = pq.poll();
            final double dist = now.distance;
            if (visited[now.num] < dist) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (graph[now.num][i] == null) {
                    continue;
                }
                final double cost = dist + graph[now.num][i];
                if (cost < visited[i]) {
                    visited[i] = cost;
                    pq.add(new Node(i, cost));
                }
            }
        }
        return visited[n - 1];
    }

    private static class Node implements Comparable<Node> {

        int num;
        double distance;

        public Node(final int num, final double distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(final Node o) {
            return Double.compare(distance, o.distance);
        }
    }
}
