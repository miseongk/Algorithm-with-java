package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_14502 {

    static int n;
    static int m;
    static int[][] map;
    static List<int[]> blank = new ArrayList<>();
    static int[] arr = new int[3];
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }
        nC3(blank.size(), 0, 0);
        System.out.println(result);
    }

    private static void nC3(int n, int index, int depth) {
        if (depth == 3) {
            result = Math.max(findArea(), result);
            return;
        }
        for (int i = index; i < n; i++) {
            arr[depth] = i;
            nC3(n, i + 1, depth + 1);
        }
    }

    private static int findArea() {
        int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            tmp[i] = map[i].clone();
        }
        final int[] a = blank.get(arr[0]);
        final int[] b = blank.get(arr[1]);
        final int[] c = blank.get(arr[2]);
        tmp[a[0]][a[1]] = 1;
        tmp[b[0]][b[1]] = 1;
        tmp[c[0]][c[1]] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmp[i][j] == 2) {
                    bfs(tmp, i, j);
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmp[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int[][] tmp, int x, int y) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (tmp[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    tmp[nx][ny] = 1;
                }
            }
        }
    }
}
