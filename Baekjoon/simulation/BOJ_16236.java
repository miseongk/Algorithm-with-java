package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16236 {

    static int n;
    static int[][] arr;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
                if (arr[i][j] == 9) {
                    x = i;
                    y = j;
                    arr[i][j] = 0;
                }
            }
        }

        int size = 2;
        int time = 0;
        int eat = 0;
        while (true) {
            final int[] result = bfs(x, y, size);
            x = result[0];
            y = result[1];
            time += result[2];
            if (result[3] == 1) {
                break;
            }
            arr[x][y] = 0;
            eat++;
            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }

    private static int[] bfs(int x, int y, final int size) {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, -1, 1, 0};
        final int[][] visited = new int[n][n];
        boolean eat = false;
        int stop = 1;
        int bx = x;
        int by = y;
        int time = 0;
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            x = now[0];
            y = now[1];
            if (eat && now[2] == time) {
                stop = 0;
                if (arr[x][y] > 0 && arr[x][y] < size) {
                    if (bx > x || (bx == x && by > y)) {
                        bx = x;
                        by = y;
                    }
                }
                continue;
            }
            for (int i = 0; i < 4; i++) {
                final int nx = x + dx[i];
                final int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] > 0) {
                    continue;
                }
                if (arr[nx][ny] > size) {
                    continue;
                } else if (arr[nx][ny] > 0 && arr[nx][ny] < size) {
                    eat = true;
                    bx = nx;
                    by = ny;
                    time = visited[x][y] + 1;
                }
                queue.offer(new int[]{nx, ny, visited[x][y] + 1});
                visited[nx][ny] = visited[x][y] + 1;
            }
        }
        return new int[]{bx, by, time, stop};
    }
}
