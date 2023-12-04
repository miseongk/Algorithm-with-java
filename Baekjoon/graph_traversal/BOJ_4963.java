package Baekjoon.graph_traversal;

import java.util.Scanner;

public class BOJ_4963 {

    static int w;
    static int h;
    static int[][] graph;

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);

        while (true) {
            int cnt = 0;

            w = sc.nextInt();
            h = sc.nextInt();
            if (w == 0 && h == 0) {
                break;
            }
            sc.nextLine();

            graph = new int[h][w];
            for (int i = 0; i < h; i++) {
                final String[] split = sc.nextLine().split(" ");
                for (int j = 0; j < w; j++) {
                    graph[i][j] = Integer.parseInt(split[j]);
                }
            }
            for (int x = 0; x < h; x++) {
                for (int y = 0; y < w; y++) {
                    if (graph[x][y] == 1) {
                        dfs(x, y);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void dfs(final int x, final int y) {
        if (x < 0 || y < 0 || x >= h || y >= w) {
            return;
        }
        if (graph[x][y] == 0) {
            return;
        }
        graph[x][y] = 0;
        dfs(x - 1, y);
        dfs(x, y - 1);
        dfs(x + 1, y);
        dfs(x, y + 1);
        dfs(x + 1, y + 1);
        dfs(x + 1, y - 1);
        dfs(x - 1, y + 1);
        dfs(x - 1, y - 1);
    }
}
