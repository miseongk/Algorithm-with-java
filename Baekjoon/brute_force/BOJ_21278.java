package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_21278 {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] visited;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            final String[] input = br.readLine().split(" ");
            graph.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
            graph.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
        }
        int min = (int) 1e9;
        int minX = 0;
        int minY = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int cnt = 0;
                visited = new int[n + 1];
                Arrays.fill(visited, (int) 1e9);
                bfs(i, j);
                for (int k = 1; k <= n; k++) {
                    cnt += visited[k] * 2;
                }
                if (cnt < min) {
                    min = cnt;
                    minX = i;
                    minY = j;
                }
            }
        }

        System.out.println(minX + " " + minY + " " + min);
    }

    private static void bfs(final int x, final int y) {
        visited[x] = 0;
        visited[y] = 0;
        final Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, 0});
        q.add(new int[]{y, 0});

        while (!q.isEmpty()) {
            final int[] now = q.poll();
            for (int i = 0; i < graph.get(now[0]).size(); i++) {
                final Integer next = graph.get(now[0]).get(i);
                if (visited[next] > now[1] + 1) {
                    visited[next] = now[1] + 1;
                    q.add(new int[]{next, now[1] + 1});
                }
            }
        }
    }
}
