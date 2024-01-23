package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2212 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int k = Integer.parseInt(br.readLine());
        final int[] sensors = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(sensors);

        final Integer[] diff = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(diff, Collections.reverseOrder());

        long result = 0;
        for (int i = k - 1; i < n - 1; i++) {
            result += diff[i];
        }
        System.out.println(result);
    }
}
