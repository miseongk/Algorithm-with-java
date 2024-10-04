package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206 {

    static int n;
    static int m;
    static int[][] graph;
    static int[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        graph = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            final String input = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        visited[0][0][0] = 1;
        bfs(0, 0);

        final int result = Math.max(visited[n - 1][m - 1][0], visited[n - 1][m - 1][1]);
        if (result == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void bfs(final int x, final int y) {
        final Queue<int[]> pq = new LinkedList<>();
        pq.add(new int[]{x, y, 1});
        while (!pq.isEmpty()) {
            final int[] now = pq.poll();
            final int nowX = now[0];
            final int nowY = now[1];
            final int wall = now[2];
            if (nowX == n - 1 && nowY == m - 1) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                final int nx = nowX + dx[i];
                final int ny = nowY + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (graph[nx][ny] == 1) {
                    if (wall == 1) {
                        if (visited[nx][ny][1] == 0) {
                            visited[nx][ny][1] = visited[nowX][nowY][0] + 1;
                            pq.add(new int[]{nx, ny, wall - 1});
                        }
                    }
                } else {
                    if (wall == 0) {
                        if (visited[nx][ny][1] == 0) {
                            visited[nx][ny][1] = visited[nowX][nowY][1] + 1;
                            pq.add(new int[]{nx, ny, wall});
                        }
                    } else {
                        if (visited[nx][ny][0] == 0) {
                            visited[nx][ny][0] = visited[nowX][nowY][0] + 1;
                            pq.add(new int[]{nx, ny, wall});
                        }
                    }
                }
            }
        }
    }
}
