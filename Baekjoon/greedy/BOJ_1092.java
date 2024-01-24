package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ_1092 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final Integer[] weight = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);
        final int m = Integer.parseInt(br.readLine());
        final Integer[] boxArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);

        Arrays.sort(weight, Collections.reverseOrder());
        Arrays.sort(boxArr, Collections.reverseOrder());
        final List<Integer> box = Arrays.stream(boxArr).collect(Collectors.toCollection(ArrayList::new));

        if (weight[0] < box.get(0)) {
            System.out.println(-1);
            return;
        }
        int result = 0;
        int j = 0;
        while (!box.isEmpty()) {
            final int now = weight[j];
            int flag = 0;
            for (int i = 0; i < box.size(); i++) {
                if (box.get(i) <= now) {
                    box.remove(i);
                    j++;
                    flag = 1;
                    break;
                }
            }
            if (j == n || flag == 0 || box.isEmpty()) {
                result++;
                j = 0;
            }
        }
        System.out.println(result);
    }
}
