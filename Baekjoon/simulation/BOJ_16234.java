package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_16234 {

    static int n;
    static int l;
    static int r;
    static int[][] arr;
    static boolean[][] visited;
    static List<int[]> unionPos;
    static int union = 0;
    static int unionCnt = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nlr = br.readLine().split(" ");
        n = Integer.parseInt(nlr[0]);
        l = Integer.parseInt(nlr[1]);
        r = Integer.parseInt(nlr[2]);
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            final String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(row[j]);
            }
        }
        int result = 0;
        while (true) {
            visited = new boolean[n][n];
            final int[][] tmp = new int[n][n];
            int flag = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        unionPos = new ArrayList<>();
                        dfs(i, j);
                        final int toBe = union / unionCnt;
                        for (final int[] pos : unionPos) {
                            tmp[pos[0]][pos[1]] = toBe;
                        }
                        if (unionCnt == 1) {
                            flag++;
                        }
                    }
                    union = 0;
                    unionCnt = 0;
                }
            }
            if (flag == n * n) {
                System.out.println(result);
                return;
            }
            result++;
            arr = Arrays.copyOf(tmp, n);
        }
    }

    private static void dfs(final int x, final int y) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        union += arr[x][y];
        unionCnt++;
        unionPos.add(new int[]{x, y});
        for (int i = 0; i < 4; i++) {
            final int nx = x + dx[i];
            final int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            final int diff = Math.abs(arr[x][y] - arr[nx][ny]);
            if (diff >= l && diff <= r) {
                dfs(nx, ny);
            }
        }
    }
}
