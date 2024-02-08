package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17276 {

    static int n;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final String[] nd = br.readLine().split(" ");
            n = Integer.parseInt(nd[0]);
            final int d = Integer.parseInt(nd[1]);
            final int[][] arr = new int[n][n];
            for (int j = 0; j < n; j++) {
                final String[] input = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(input[k]);
                }
            }
            final int[][] tmpArr = new int[n][n];
            for (int j = 0; j < n; j++) {
                tmpArr[j] = Arrays.copyOf(arr[j], n);
            }
            final int cnt = d / 45;
            final int[][] result;
            if (cnt == 8) {
                result = tmpArr;
            } else if (cnt < 0) {
                result = reverseTurn(cnt, tmpArr, arr);
            } else {
                result = turn(cnt, tmpArr, arr);
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    sb.append(result[j][k]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int[][] turn(final int cnt, final int[][] tmpArr, final int[][] arr) {
        for (int i = 0; i < cnt; i++) {
            final int middle = (n + 1) / 2;
            // 주 대각선 -> 가운데 열
            for (int j = 0; j < n; j++) {
                tmpArr[j][middle - 1] = arr[j][j];
            }
            // 가운데 열 -> 부 대각선
            for (int j = 0; j < n; j++) {
                tmpArr[j][n - j - 1] = arr[j][middle - 1];
            }
            // 부 대각선 -> 가운데 행
            for (int j = 0; j < n; j++) {
                tmpArr[middle - 1][n - j - 1] = arr[j][n - j - 1];
            }
            // 가운데 행 -> 주 대각선
            for (int j = 0; j < n; j++) {
                tmpArr[j][j] = arr[middle - 1][j];
            }
            for (int j = 0; j < n; j++) {
                arr[j] = Arrays.copyOf(tmpArr[j], n);
            }
        }
        return tmpArr;
    }

    private static int[][] reverseTurn(final int cnt, final int[][] tmpArr, final int[][] arr) {
        for (int i = 0; i < Math.abs(cnt); i++) {
            final int middle = (n + 1) / 2;
            // 주 대각선 -> 가운데 행
            for (int j = 0; j < n; j++) {
                tmpArr[middle - 1][j] = arr[j][j];
            }
            // 가운데 행 -> 부 대각선
            for (int j = 0; j < n; j++) {
                tmpArr[j][n - j - 1] = arr[middle - 1][n - j - 1];
            }
            // 부 대각선 -> 가운데 열
            for (int j = 0; j < n; j++) {
                tmpArr[j][middle - 1] = arr[j][n - j - 1];
            }
            // 가운데 열 -> 주 대각선
            for (int j = 0; j < n; j++) {
                tmpArr[j][j] = arr[j][middle - 1];
            }
            for (int j = 0; j < n; j++) {
                arr[j] = Arrays.copyOf(tmpArr[j], n);
            }
        }
        return tmpArr;
    }
}
