package Softeer.Level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SFTR_7726 {

    static String[][] map;
    static List<int[]> ghost = new ArrayList<>();
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new String[n][m];
        int x = 0;
        int y = 0;
        int dX = 0;
        int dY = 0;
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = String.valueOf(input.charAt(j));
                if (map[i][j].equals("G")) {
                    ghost.add(new int[]{i, j});
                } else if (map[i][j].equals("N")) {
                    x = i;
                    y = j;
                } else if (map[i][j].equals("D")) {
                    dX = i;
                    dY = j;
                }
            }
        }
        int ghostMin = n * m;
        if (ghost.size() > 0) {
            for (int i = 0; i < ghost.size(); i++) {
                ghostMin = Math.min(Math.abs(dX - ghost.get(i)[0]) + Math.abs(dY - ghost.get(i)[1]), ghostMin);
            }
        }
        int manMin = findShortest(new int[]{x, y});
        if (manMin == -1 || ghostMin <= manMin) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    private static int findShortest(int[] pos) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        queue.add(new int[]{pos[0], pos[1], 0});
        visited[pos[0]][pos[1]] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now[0];
                int ny = dy[i] + now[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny].equals(".")) {
                    queue.add(new int[]{nx, ny, now[2] + 1});
                    visited[nx][ny] = true;
                }
                if (map[nx][ny].equals("D")) {
                    return now[2] + 1;
                }
            }
        }
        return -1;
    }
}
