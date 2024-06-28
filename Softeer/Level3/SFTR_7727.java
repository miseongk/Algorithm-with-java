package Softeer.Level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SFTR_7727 {

    static int n;
    static int m;
    static int[][] matrix;
    static List<List<int[][]>> routes = new ArrayList<>();
    static int[][] path;
    static int[] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }
        for (int i = 0; i < m; i++) {
            routes.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            findRoutes(i, x, y);
        }
        arr = new int[m];
        findMax(0);
        System.out.println(answer);
    }

    private static void findRoutes(int now, int x, int y) {
        boolean[][] visited = new boolean[n][n];
        path = new int[4][2];
        visited[x][y] = true;
        path[0][0] = x;
        path[0][1] = y;
        dfs(now, x, y, 0, visited);

    }

    private static void dfs(int nowNum, int x, int y, int depth, boolean[][] visited) {
        if (depth == 3) {
            int[][] copyPath = new int[4][2];
            for (int i = 0; i < 4; i++) {
                copyPath[i] = path[i].clone();
            }
            routes.get(nowNum).add(copyPath);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            path[depth + 1][0] = nx;
            path[depth + 1][1] = ny;
            dfs(nowNum, nx, ny, depth + 1, visited);
            visited[nx][ny] = false;
        }
    }

    private static void findMax(int index) {
        if (index == m) {
            answer = Math.max(calSum(), answer);
            return;
        }
        for (int i = 0; i < routes.get(index).size(); i++) {
            arr[index] = i;
            findMax(index + 1);
        }
    }

    private static int calSum() {
        int sum = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[routes.get(j).get(arr[j])[i][0]][routes.get(j).get(arr[j])[i][1]]) {
                    continue;
                }
                sum += matrix[routes.get(j).get(arr[j])[i][0]][routes.get(j).get(arr[j])[i][1]];
                visited[routes.get(j).get(arr[j])[i][0]][routes.get(j).get(arr[j])[i][1]] = true;
            }
        }
        return sum;
    }
}
