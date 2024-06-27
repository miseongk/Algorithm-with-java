package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18430 {

    static int n;
    static int m;
    static int[][] wood;
    static int[][] dPos = {{-1, 0, 0, -1}, {-1, 0, 0, 1}, {0, -1, 1, 0}, {0, 1, 1, 0}};
    static boolean[][] visited;
    static int result = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        wood = new int[n][m];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                wood[i][j] = Integer.parseInt(input[j]);
            }
        }
        visited = new boolean[n][m];
        dfs(0, 0);
        System.out.println(result);
    }

    private static void dfs(int pos, int sum) {
        if (pos == n * m) {
            result = Math.max(sum, result);
            return;
        }
        int x = pos / m;
        int y = pos % m;
        if (!visited[x][y]) {
            for (int i = 0; i < 4; i++) {
                final int nx1 = dPos[i][0] + x;
                final int ny1 = dPos[i][1] + y;
                final int nx2 = dPos[i][2] + x;
                final int ny2 = dPos[i][3] + y;
                if (isPossible(nx1, ny1, nx2, ny2)) {
                    visited[x][y] = true;
                    visited[nx1][ny1] = true;
                    visited[nx2][ny2] = true;
                    dfs(pos + 1, sum + (wood[x][y] * 2 + wood[nx1][ny1] + wood[nx2][ny2]));
                    visited[x][y] = false;
                    visited[nx1][ny1] = false;
                    visited[nx2][ny2] = false;
                }
            }
        }
        dfs(pos + 1, sum);
    }

    private static boolean isPossible(int nx1, int ny1, int nx2, int ny2) {
        if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m || visited[nx1][ny1]) {
            return false;
        }
        if (nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m || visited[nx2][ny2]) {
            return false;
        }
        return true;
    }
}
