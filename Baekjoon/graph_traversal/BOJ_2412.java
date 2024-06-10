package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2412 {

    static int t;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nt = br.readLine().split(" ");
        final int n = Integer.parseInt(nt[0]);
        t = Integer.parseInt(nt[1]);
        for (int i = 0; i <= t; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            final String[] xy = br.readLine().split(" ");
            final int x = Integer.parseInt(xy[0]);
            final int y = Integer.parseInt(xy[1]);
            graph.get(y).add(x);
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        final Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0}); // x, y, depth
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            for (int dy = -2; dy <= 2; dy++) {
                final int ny = now[1] + dy;
                if (ny < 0 || ny > t) {
                    continue;
                }
                for (int i = 0; i < graph.get(ny).size(); i++) {
                    final Integer nx = graph.get(ny).get(i);
                    if (Math.abs(nx - now[0]) > 2) {
                        continue;
                    }
                    if (ny == t) {
                        return now[2] + 1;
                    }
                    queue.add(new int[]{nx, ny, now[2] + 1});
                    graph.get(ny).remove(i);
                    i--;
                }
            }
        }
        return -1;
    }
}
