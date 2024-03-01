package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_21758 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] honey = new int[n];
        final int[] sumHoney = new int[n];
        final String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            honey[i] = Integer.parseInt(input[i]);
            if (i == 0) {
                sumHoney[i] = honey[i];
                continue;
            }
            sumHoney[i] = sumHoney[i - 1] + honey[i];
        }
        int oneBee = 0;
        int twoBee = 1;
        int result = (sumHoney[n - 1] - sumHoney[twoBee]) * 2;
        for (int i = twoBee + 1; i < n; i++) {
            final int next = (sumHoney[n - 1] - sumHoney[i]) + sumHoney[n - 1] - sumHoney[oneBee] - honey[i];
            result = Math.max(result, next);
        }

        oneBee = n - 1;
        twoBee = n - 2;
        for (int i = twoBee; i > 0; i--) {
            final int next = sumHoney[n - 2] - honey[i] + sumHoney[i - 1];
            result = Math.max(result, next);
        }
        final int tmp = honey[0] + honey[n - 1];
        honey[0] = 0;
        honey[n - 1] = 0;
        final int max = Arrays.stream(honey)
                .max()
                .getAsInt();
        result = Math.max(max + sumHoney[n - 1] - tmp, result);

        System.out.println(result);
    }
}
