package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14712 {

    static int n;
    static int m;
    static int result = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        final boolean[][] visited = new boolean[n][m];
        findEndCond(visited, 0, 0);
        System.out.println(result);
    }

    private static void findEndCond(final boolean[][] visited, final int x, final int y) {
        if (x == n) {
            for (int i = 0; i <= n - 2; i++) {
                for (int j = 0; j <= m - 2; j++) {
                    if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) {
                        return;
                    }
                }
            }
            result++;
            return;
        }

        final int nextX;
        final int nextY;
        if (y + 1 < m) {
            nextX = x;
            nextY = y + 1;
        } else {
            nextX = x + 1;
            nextY = 0;
        }
        visited[x][y] = true;
        findEndCond(visited, nextX, nextY);
        visited[x][y] = false;
        findEndCond(visited, nextX, nextY);
    }
}
