package PGS.Level3;

import java.util.HashMap;
import java.util.Map;

public class PGS_150365 {

    boolean[][][] visited;
    String answer = "";
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    Map<Integer, String> directMap = new HashMap<>();
    boolean flag = false;

    public String solution(final int n, final int m, final int x, final int y, final int r, final int c, final int k) {
        visited = new boolean[n + 2][m + 2][k + 2];
        directMap.put(0, "d");
        directMap.put(1, "l");
        directMap.put(2, "r");
        directMap.put(3, "u");

        dfs(x, y, k, "", n, m, r, c);

        if (answer.isEmpty()) {
            return "impossible";
        }
        return answer;
    }

    private void dfs(final int x, final int y, final int remain, final String direction, final int n, final int m,
                     final int r, final int c) {
        if (flag) {
            return;
        }
        if (remain == 0) {
            if (x == r && y == c) {
                answer = direction;
                flag = true;
            }
            return;
        }
        if (visited[x][y][remain]) {
            return;
        }
        visited[x][y][remain] = true;
        for (int i = 0; i < 4; i++) {
            final int nx = dx[i] + x;
            final int ny = dy[i] + y;
            if (nx < 1 || nx > n || ny < 1 || ny > m) {
                continue;
            }
            dfs(nx, ny, remain - 1, direction + directMap.get(i), n, m, r, c);
        }
    }
}
