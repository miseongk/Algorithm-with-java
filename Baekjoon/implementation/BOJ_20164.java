package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20164 {

    static int min = (int) 1e9;
    static int max = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String n = br.readLine();
        calOddNum(n, 0);
        System.out.println(min + " " + max);
    }

    private static void calOddNum(final String str, int cnt) {
        int sum = 0;
        if (str.length() > 3) {
            for (int i = 0; i < str.length(); i++) {
                if (isOdd(str.charAt(i))) {
                    cnt++;
                }
            }
            for (int i = 0; i < 1; i++) {
                for (int j = i + 1; j < str.length() - 1; j++) {
                    for (int k = j + 1; k < str.length(); k++) {
                        sum = 0;
                        sum += Integer.parseInt(String.valueOf(str.substring(i, j)));
                        sum += Integer.parseInt(String.valueOf(str.substring(j, k)));
                        sum += Integer.parseInt(String.valueOf(str.substring(k)));
                        calOddNum(String.valueOf(sum), cnt);
                    }
                }
            }
        } else if (str.length() == 2 || str.length() == 3) {
            for (int i = 0; i < str.length(); i++) {
                if (isOdd(str.charAt(i))) {
                    cnt++;
                }
                sum += Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            calOddNum(String.valueOf(sum), cnt);
        } else {
            if (isOdd(str.charAt(0))) {
                cnt++;
            }
            min = Math.min(cnt, min);
            max = Math.max(cnt, max);
        }
    }

    private static boolean isOdd(final char c) {
        return Integer.parseInt(String.valueOf(c)) % 2 == 1;
    }
}
