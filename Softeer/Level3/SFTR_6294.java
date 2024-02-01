package Softeer.Level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SFTR_6294 {

    public static void main(final String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final String[] nk = br.readLine().split(" ");
        final int n = Integer.parseInt(nk[0]);
        final int k = Integer.parseInt(nk[1]);
        final String[] s = br.readLine().split(" ");

        final int[] sumArr = new int[n + 1];
        sumArr[0] = 0;
        for (int i = 1; i <= n; i++) {
            sumArr[i] = sumArr[i - 1] + Integer.parseInt(s[i - 1]);
        }

        for (int i = 0; i < k; i++) {
            final String[] ab = br.readLine().split(" ");
            final int a = Integer.parseInt(ab[0]);
            final int b = Integer.parseInt(ab[1]);
            final double arg = Math.round((sumArr[b] - sumArr[a - 1]) / ((b - a + 1) * 1.0) * 100) / 100.0;
            sb.append(String.format("%.2f", arg)).append("\n");
        }
        System.out.println(sb);
    }
}

