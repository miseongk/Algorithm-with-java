package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928 {

    static int[][] board = new int[107][1];

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        for (int i = 0; i < n + m; i++) {
            final String[] xy = br.readLine().split(" ");
            board[Integer.parseInt(xy[0])][0] = Integer.parseInt(xy[1]);
        }
        final int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        final int[] visited = new int[107];
        Arrays.fill(visited, (int) 1e9);
        final Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{1, 0});
        visited[1] = 0;
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            final int position = now[0];
            final int level = now[1];
            if (position == 100) {
                break;
            }
            for (int i = 1; i <= 6; i++) {
                if (board[position + i][0] != 0) {
                    queue.add(new int[]{board[position + i][0], level + 1});
                    visited[board[position + i][0]] = level + 1;
                    continue;
                }
                if (visited[position + i] > level + 1) {
                    queue.add(new int[]{position + i, level + 1});
                    visited[position + i] = level + 1;
                }
            }
        }
        return visited[100];
    }
}
