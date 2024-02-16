package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17836 {

    static int[][] arr;
    static int[][][] visited;
    static int n;
    static int m;
    static int t;
    static int result = (int) 1e9;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nmt = br.readLine().split(" ");
        n = Integer.parseInt(nmt[0]);
        m = Integer.parseInt(nmt[1]);
        t = Integer.parseInt(nmt[2]);
        arr = new int[n][m];
        visited = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }
        bfs();
        if (result > 0 && result <= t) {
            System.out.println(result);
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
                if (visited[x][y][0] == 0) {
                    result = visited[x][y][1];
                } else if (visited[x][y][1] == 0) {
                    result = visited[x][y][0];
                } else {
                    result = Math.min(visited[x][y][0], visited[x][y][1]);
                }
                break;
            }
            for (int i = 0; i < 4; i++) {
                final int nx = dx[i] + x;
                final int ny = dy[i] + y;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny][0] > 0 && gram == 0) {
                    continue;
                }
                if (visited[nx][ny][1] > 0 && gram == 1) {
                    continue;
                }
                if (gram == 1) {
                    queue.offer(new int[]{nx, ny, gram});
                    visited[nx][ny][1] = visited[x][y][1] + 1;
                } else {
                    if (arr[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, gram});
                        visited[nx][ny][0] = visited[x][y][0] + 1;
                    } else if (arr[nx][ny] == 2) {
                        queue.offer(new int[]{nx, ny, 1});
                        visited[nx][ny][1] = visited[x][y][0] + 1;
                        visited[nx][ny][0] = visited[x][y][0] + 1;
                    }
                }
            }
        }
    }
}
