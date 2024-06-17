package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_5547 {

    static int w;
    static int h;
    static int[][] wall;
    static boolean[][] visited;
    static int[] dxEven = {-1, 0, -1, 1, -1, 0};
    static int[] dyEven = {-1, -1, 0, 0, 1, 1};
    static int[] dxOdd = {0, 1, -1, 1, 0, 1};
    static int[] dyOdd = {-1, -1, 0, 0, 1, 1};
    static boolean noInternal;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] wh = br.readLine().split(" ");
        w = Integer.parseInt(wh[0]);
        h = Integer.parseInt(wh[1]);
        wall = new int[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            final String[] inputs = br.readLine().split(" ");
            for (int j = 1; j <= w; j++) {
                wall[i][j] = Integer.parseInt(inputs[j - 1]);
            }
        }
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (wall[i][j] == 0) {
                    final Queue<int[]> queue = new LinkedList<>();
                    visited = new boolean[h + 1][w + 1];
                    noInternal = false;
                    findInternal(queue, j, i);
                    if (noInternal) {
                        checkWall(queue, -1);
                    } else {
                        checkWall(queue, -2);
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (wall[i][j] == 1) {
                    final Queue<int[]> queue = new LinkedList<>();
                    visited = new boolean[h + 1][w + 1];
                    findBuilding(queue, j, i);
                    sum += calculateWallLength(queue);
                }
            }
        }
        System.out.println(sum);
    }

    private static void findInternal(final Queue<int[]> queue, final int x, final int y) {
        if (y == 1 || y == h || x == 1 || x == w) {
            noInternal = true;
        }
        visited[y][x] = true;
        queue.add(new int[]{x, y});
        for (int i = 0; i < 6; i++) {
            final int nx;
            final int ny;
            if (y % 2 == 0) {
                nx = dxEven[i] + x;
                ny = dyEven[i] + y;
            } else {
                nx = dxOdd[i] + x;
                ny = dyOdd[i] + y;
            }
            if (nx < 1 || nx > w || ny < 1 || ny > h) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }
            if (wall[ny][nx] == 0) {
                findInternal(queue, nx, ny);
            }
        }
    }

    private static void checkWall(final Queue<int[]> queue, final int checkNum) {
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            wall[now[1]][now[0]] = checkNum;
        }
    }

    private static void findBuilding(final Queue<int[]> queue, final int x, final int y) {
        visited[y][x] = true;
        queue.add(new int[]{x, y});
        for (int i = 0; i < 6; i++) {
            final int nx;
            final int ny;
            if (y % 2 == 0) {
                nx = dxEven[i] + x;
                ny = dyEven[i] + y;
            } else {
                nx = dxOdd[i] + x;
                ny = dyOdd[i] + y;
            }
            if (nx < 1 || nx > w || ny < 1 || ny > h) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }
            if (wall[ny][nx] == 1) {
                findBuilding(queue, nx, ny);
            }
        }
    }

    private static int calculateWallLength(final Queue<int[]> queue) {
        int wallLength = 0;
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            final int x = now[0];
            final int y = now[1];
            wall[y][x] = 2;
            for (int i = 0; i < 6; i++) {
                final int nx;
                final int ny;
                if (y % 2 == 0) {
                    nx = dxEven[i] + x;
                    ny = dyEven[i] + y;
                } else {
                    nx = dxOdd[i] + x;
                    ny = dyOdd[i] + y;
                }
                if (nx < 1 || nx > w || ny < 1 || ny > h) {
                    wallLength++;
                    continue;
                }
                if (wall[ny][nx] == -1) {
                    wallLength++;
                }
            }
        }
        return wallLength;
    }
}
