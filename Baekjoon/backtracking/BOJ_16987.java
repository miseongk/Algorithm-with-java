package Baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16987 {

    static int n;
    static int result = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        final int[][] eggs = new int[n][2];
        for (int i = 0; i < n; i++) {
            final String[] sw = br.readLine().split(" ");
            final int s = Integer.parseInt(sw[0]);
            final int w = Integer.parseInt(sw[1]);
            eggs[i][0] = s;
            eggs[i][1] = w;
        }
        hit(0, 0, eggs);
        System.out.println(result);
    }

    private static void hit(final int beforeEggIdx, final int beforeCnt, final int[][] eggs) {
        if (beforeEggIdx == n) {
            result = Math.max(beforeCnt, result);
            return;
        }
        if (eggs[beforeEggIdx][0] <= 0 || beforeCnt == n - 1) {
            hit(beforeEggIdx + 1, beforeCnt, eggs);
            return;
        }
        final int[][] tmp = new int[n][2];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i == beforeEggIdx || eggs[i][0] <= 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                System.arraycopy(eggs[j], 0, tmp[j], 0, 2);
            }
            cnt = 0;
            if (tmp[i][0] > 0) {
                tmp[beforeEggIdx][0] -= tmp[i][1];
                tmp[i][0] -= tmp[beforeEggIdx][1];
                if (tmp[beforeEggIdx][0] <= 0) {
                    cnt++;
                }
                if (tmp[i][0] <= 0) {
                    cnt++;
                }
            }
            hit(beforeEggIdx + 1, beforeCnt + cnt, tmp);
        }
    }
}
