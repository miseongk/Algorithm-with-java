package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15661 {

    static int n;
    static int[] arr;
    static boolean[] visited;
    static int[][] ability;
    static int answer = (int) 1e9;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ability = new int[n][n];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                ability[i][j] = Integer.parseInt(input[j]);
            }
        }
        visited = new boolean[n];
        nCr(0);
        System.out.println(answer);
    }

    private static void nCr(final int num) {
        if (num == n) {
            answer = Math.min(calculateAbility(), answer);
            return;
        }
        visited[num] = true;
        nCr(num + 1);
        visited[num] = false;
        nCr(num + 1);
    }

    private static int calculateAbility() {
        int teamA = 0;
        int teamB = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    teamA += ability[i][j] + ability[j][i];
                } else if (!visited[i] && !visited[j]) {
                    teamB += ability[i][j] + ability[j][i];
                }
            }
        }

        return Math.abs(teamA - teamB);
    }
}
