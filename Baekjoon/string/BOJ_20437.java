package Baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437 {

    static final StringBuilder sb = new StringBuilder();

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final String w = br.readLine();
            final int k = Integer.parseInt(br.readLine());
            if (k == 1) {
                sb.append(1 + " " + 1).append("\n");
                continue;
            }
            final int[] minAndMax = findMinAndMax(w, k);
            final int min = minAndMax[0];
            final int max = minAndMax[1];
            if (min == -1) {
                sb.append(-1).append("\n");
            } else {
                sb.append(min).append(" ").append(max).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int[] findMinAndMax(final String w, final int k) {
        int max = 0;
        int min = 10001;
        for (int i = 0; i < w.length(); i++) {
            final char start = w.charAt(i);
            int cnt = 1;
            for (int j = i + 1; j < w.length(); j++) {
                if (start == w.charAt(j)) {
                    cnt++;
                }
                if (cnt == k) {
                    final int length = j - i + 1;
                    max = Math.max(length, max);
                    min = Math.min(length, min);
                    break;
                }
            }
        }
        if (min == 10001) {
            return new int[]{-1, -1};
        }
        return new int[]{min, max};
    }
}
