package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17836 {

    static int[][] arr;
    static int[][] visited;
    static int n;
    static int m;
    static int t;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nmt = br.readLine().split(" ");
        n = Integer.parseInt(nmt[0]);
        m = Integer.parseInt(nmt[1]);
        t = Integer.parseInt(nmt[2]);
        arr = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }
        bfs();
        if (visited[n - 1][m - 1] > 0 && visited[n - 1][m - 1] <= t) {
            System.out.println(visited[n - 1][m - 1]);
        } else {
            System.out.println("Fail");
        }
    }

    private static void bfs() {
        final int[] dx = {0, 0, -1, 1};
        final int[] dy = {-1, 1, 0, 0};
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            final int[] pos = queue.poll();
            final int x = pos[0];
            final int y = pos[1];
            final int gram = pos[2];
            if (x == n - 1 && y == m - 1) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                final int nx = dx[i] + x;
                final int ny = dy[i] + y;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny] > 0 && gram == 0) {
                    continue;
                }
                if (arr[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, gram});
                }
                if (arr[nx][ny] == 1 && gram == 1) {
                    queue.offer(new int[]{nx, ny, gram});
                } else if (arr[nx][ny] == 1 && gram == 0) {
                    continue;
                }
                if (arr[nx][ny] == 2) {
                    queue.offer(new int[]{nx, ny, 1});
                }
                visited[nx][ny] = visited[x][y] + 1;
            }
        }
    }
}
