package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1025 {

    static int n;
    static int m;
    static int[][] matrix;
    static long result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        matrix = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            final String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                matrix[i][j] = input.charAt(j) - '0';
            }
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < m; l++) {
                for (int i = -n; i <= n; i++) {
                    for (int j = -m; j <= m; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        makeNumAndJudge(k, l, i, j);
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void makeNumAndJudge(int nowX, int nowY, int xDiff, int yDiff) {
        long num = 0;
        while (true) {
            if (nowX < 0 || nowX >= n || nowY < 0 || nowY >= m) {
                break;
            }
            num += matrix[nowX][nowY];
            if (isSquareNumber(num)) {
                result = Math.max(num, result);
            }
            nowX += xDiff;
            nowY += yDiff;
            num *= 10;
        }
    }

    private static boolean isSquareNumber(long num) {
        final double sqrt = Math.sqrt(num);
        return sqrt % 1 == 0.0;
    }
}
