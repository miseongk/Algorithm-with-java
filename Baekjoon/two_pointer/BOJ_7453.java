package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_7453 {

    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            a[i] = Integer.parseInt(input[0]);
            b[i] = Integer.parseInt(input[1]);
            c[i] = Integer.parseInt(input[2]);
            d[i] = Integer.parseInt(input[3]);
        }

        final long[] sumAB = new long[n * n];
        final long[] sumCD = new long[n * n];
        int idxAB = 0;
        int idxCD = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[idxAB++] = a[i] + b[j];
                sumCD[idxCD++] = c[i] + d[j];
            }
        }
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        final long cnt = findZero(sumAB, sumCD);
        System.out.println(cnt);
    }

    private static long findZero(final long[] sumAB, final long[] sumCD) {
        int s = 0;
        int e = sumCD.length - 1;
        long cnt = 0;
        while (s < sumAB.length && e >= 0) {
            final long result = sumAB[s] + sumCD[e];
            if (result == 0) {
                int nextS = s + 1;
                int nextE = e - 1;
                while (nextS < sumAB.length && sumAB[s] == sumAB[nextS]) {
                    nextS++;
                }
                while (nextE >= 0 && sumCD[e] == sumCD[nextE]) {
                    nextE--;
                }
                cnt += (long) (nextS - s) * (e - nextE);
                s = nextS;
                e = nextE;
            } else if (result > 0) {
                e--;
            } else {
                s++;
            }
        }
        return cnt;
    }
}

