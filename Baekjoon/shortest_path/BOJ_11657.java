package Baekjoon.shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_11657 {

    static int n;
    static int m;
    static List<Edge> edges = new ArrayList<>();
    static long[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        for (int i = 0; i < m; i++) {
            final String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            edges.add(new Edge(a, b, c));
        }
        distance = new long[n + 1];
        Arrays.fill(distance, (int) 1e9);
        final boolean negativeCycle = bellmanFord(1);
        if (negativeCycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= n; i++) {
                if (distance[i] == (int) 1e9) {
                    System.out.println(-1);
                } else {
                    System.out.println(distance[i]);
                }
            }
        }
    }

    private static boolean bellmanFord(int start) {
        distance[start] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                final Edge edge = edges.get(j);
                if (distance[edge.start] == (int) 1e9) {
                    continue;
                }
                if (distance[edge.end] > distance[edge.start] + edge.dist) {
                    distance[edge.end] = distance[edge.start] + edge.dist;
                    if (i == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Edge {

        int start;
        int end;
        int dist;

        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}
