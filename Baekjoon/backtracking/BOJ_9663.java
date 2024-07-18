package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {

    static int n;
    static boolean[][] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            dfs(1, visited);
            visited[0][i] = false;
        }
        System.out.println(cnt);
    }

    private static void dfs(int row, boolean[][] visited) {
        if (row == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isPossible(row, i, visited)) {
                visited[row][i] = true;
                dfs(row + 1, visited);
                visited[row][i] = false;
            }
        }
    }

    private static boolean isPossible(int x, int y, boolean[][] visited) {
        for (int queenX = 0; queenX < n; queenX++) {
            for (int queenY = 0; queenY < n; queenY++) {
                if (!visited[queenX][queenY]) {
                    continue;
                }
                if (y == queenY) {
                    return false;
                }
                if (x - queenX == queenY - y) {
                    return false;
                }
                if (x - queenX == y - queenY) {
                    return false;
                }
            }
        }
        return true;
    }
}
