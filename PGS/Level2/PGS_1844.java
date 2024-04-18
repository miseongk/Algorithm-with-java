package PGS.Level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PGS_1844 {

    public int solution(final int[][] maps) {
        final int n = maps.length;
        final int m = maps[0].length;
        System.out.println(n + " " + m);
        final int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], (int) 1e9);
        }
        final int[] dx = {0, 0, 1, -1};
        final int[] dy = {1, -1, 0, 0};

        final Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            final int x = now[0];
            final int y = now[1];
            for (int i = 0; i < 4; i++) {
                final int nx = x + dx[i];
                final int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (maps[nx][ny] == 0) {
                    continue;
                }
                if (visited[nx][ny] <= visited[x][y] + 1) {
                    continue;
                }
                visited[nx][ny] = visited[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }
        if (visited[n - 1][m - 1] == (int) 1e9) {
            return -1;
        }
        return visited[n - 1][m - 1];
    }
}
