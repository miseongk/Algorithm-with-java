package Baekjoon.graph_traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3184 {

    static char[][] graph;
    static int n = 0;
    static int m = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String[] split = sc.nextLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            final String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        int wolf = 0;
        int sheep = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] != '-' && graph[i][j] != '#') {
                    final int[] wolfAndSheep = bfs(i, j);
                    wolf += wolfAndSheep[0];
                    sheep += wolfAndSheep[1];
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    public static int[] bfs(final int a, final int b) {
        final Queue<int[]> queue = new LinkedList<>();
        int wolf = 0;
        int sheep = 0;

        if (graph[a][b] == 'o') {
            sheep++;
        } else if (graph[a][b] == 'v') {
            wolf++;
        }
        graph[a][b] = '-';
        queue.offer(new int[]{a, b});
        while (!queue.isEmpty()) {
            final int[] xy = queue.poll();
            final int x = xy[0];
            final int y = xy[1];
            for (int i = 0; i < 4; i++) {
                final int nx = x + dx[i];
                final int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (graph[nx][ny] != '-' && graph[nx][ny] != '#') {
                    queue.add(new int[]{nx, ny});
                    if (graph[nx][ny] == 'o') {
                        sheep++;
                    } else if (graph[nx][ny] == 'v') {
                        wolf++;
                    }
                    graph[nx][ny] = '-';
                }
            }
        }

        if (wolf >= sheep) {
            return new int[]{wolf, 0};
        } else {
            return new int[]{0, sheep};
        }
    }
}
