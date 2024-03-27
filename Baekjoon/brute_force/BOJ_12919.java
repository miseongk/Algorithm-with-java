package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {

    static String t;
    static boolean flag = false;
    static int answer = 0;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String s = br.readLine();
        t = br.readLine();

        game(s);
        System.out.println(answer);
    }

    private static void game(final String s) {
        if (flag) {
            return;
        }
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                flag = true;
                answer = 1;
            }
            return;
        }
        if (!t.contains(s) && !t.contains(reverse(s))) {
            return;
        }
        game(s + "A");
        final String reversed = reverse(s + "B");
        game(reversed);
    }

    private static String reverse(final String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
