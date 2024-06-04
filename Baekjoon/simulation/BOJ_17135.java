package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ_17135 {

    static int n;
    static int m;
    static int d;
    static int[][] enemy;
    static int[] archerPos = new int[3];
    static boolean[] visited;
    static int maxKill = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nmd = br.readLine().split(" ");
        n = Integer.parseInt(nmd[0]);
        m = Integer.parseInt(nmd[1]);
        d = Integer.parseInt(nmd[2]);
        enemy = new int[n][m];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                enemy[i][j] = Integer.parseInt(input[j]);
            }
        }
        visited = new boolean[m];
        findArcherPos(0, 0);
        System.out.println(maxKill);
    }

    private static void findArcherPos(final int depth, final int idx) {
        if (depth == 3) {
            maxKill = Math.max(cntKill(archerPos), maxKill);
            return;
        }
        for (int i = idx; i < m; i++) {
            if (visited[i]) {
                continue;
            }
            archerPos[depth] = i;
            visited[i] = true;
            findArcherPos(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static int cntKill(final int[] archerPos) {
        final int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(enemy[i], 0, tmp[i], 0, m);
        }
        int cnt = 0;
        final int[][] archers = new int[3][2];
        for (int i = 0; i < 3; i++) {
            archers[i][0] = n;
            archers[i][1] = archerPos[i];
        }
        for (int h = 0; h < n; h++) {
            final Set<String> killEnemies = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                int minDist = 50;
                int x = -1;
                int y = -1;
                for (int j = n - 1; j >= 0; j--) {
                    for (int k = 0; k < m; k++) {
                        if (tmp[j][k] == 0) {
                            continue;
                        }
                        final int dist = Math.abs(archers[i][0] - j) + Math.abs(archers[i][1] - k);
                        if (dist > d) {
                            continue;
                        }
                        if (dist <= d && dist <= minDist) {
                            if (minDist == dist) {
                                if (y < k) {
                                    continue;
                                }
                            }
                            minDist = dist;
                            x = j;
                            y = k;
                        }
                    }
                }
                if (!(x == -1 && y == -1)) {
                    killEnemies.add(x + "," + y);
                }
            }
            cnt += killEnemies.size();
            for (final String killEnemy : killEnemies) {
                final String[] split = killEnemy.split(",");
                tmp[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = 0;
            }
            for (int i = n - 1; i > 0; i--) {
                for (int j = 0; j < m; j++) {
                    tmp[i][j] = tmp[i - 1][j];
                }
            }
            Arrays.fill(tmp[0], 0);
        }
        return cnt;
    }
}
