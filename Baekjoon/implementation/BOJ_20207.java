package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20207 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] day = new int[367];
        for (int i = 0; i < n; i++) {
            final String[] se = br.readLine().split(" ");
            final int s = Integer.parseInt(se[0]);
            final int e = Integer.parseInt(se[1]);
            for (int j = s; j <= e; j++) {
                day[j]++;
            }
        }
        int result = 0;
        int start = 1;
        int i = 1;
        int max = 0;
        while (true) {
            int now = day[i];
            while (now == 0 && i < 366) {
                i++;
                now = day[i];
            }
            if (i == 366) {
                break;
            }
            start = i;
            max = now;
            while (now > 0 && i < 366) {
                i++;
                now = day[i];
                max = Math.max(now, max);
            }
            result += max * (i - start);
        }
        // 0이 아니면 max값을 구하고 길이를 추가/ 0이면 result에 값을 더해주면 간단하게 할 수 있음
        System.out.println(result);
    }
}
