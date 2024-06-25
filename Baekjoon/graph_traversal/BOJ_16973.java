package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16973 {

    static int n;
    static int m;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        matrix = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = Integer.parseInt(input[j - 1]);
            }
        }
        final String[] input = br.readLine().split(" ");
        int h = Integer.parseInt(input[0]);
        int w = Integer.parseInt(input[1]);
        int[] s = new int[]{Integer.parseInt(input[2]), Integer.parseInt(input[3])};
        int[] f = new int[]{Integer.parseInt(input[4]), Integer.parseInt(input[5])};
        final int result = bfs(h, w, s, f);
        System.out.println(result);
    }

    private static int bfs(int h, int w, int[] s, int[] f) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{s[0], s[1], 0});
        matrix[s[0]][s[1]] = 2;
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            if (now[0] == f[0] && now[1] == f[1]) {
                return now[2];
            }
            for (int i = 0; i < 4; i++) {
                boolean flag = false;
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m || matrix[nx][ny] == 2) {
                    continue;
                }
                if (i % 2 == 0) {
                    for (int j = now[1]; j < now[1] + w; j++) {
                        nx = (i == 2) ? now[0] + h - 1 + dx[i] : now[0] + dx[i];
                        ny = j + dy[i];
                        if (nx < 1 || nx > n || ny < 1 || ny > m || matrix[nx][ny] == 1) {
                            flag = true;
                            break;
                        }
                    }
                } else {
                    for (int j = now[0]; j < now[0] + h; j++) {
                        nx = j + dx[i];
                        ny = (i == 1) ? now[1] + w - 1 + dy[i] : now[1] + dy[i];
                        if (nx < 1 || nx > n || ny < 1 || ny > m || matrix[nx][ny] == 1) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    continue;
                }
                queue.add(new int[]{now[0] + dx[i], now[1] + dy[i], now[2] + 1});
                matrix[now[0] + dx[i]][now[1] + dy[i]] = 2;
            }
        }
        return -1;
    }
}
