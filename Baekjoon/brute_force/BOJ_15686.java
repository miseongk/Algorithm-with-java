package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_15686 {

    static List<int[]> house = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static int[] arr;
    static int result = (int) 1e9;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        for (int i = 0; i < n; i++) {
            final String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                final int now = Integer.parseInt(inputs[j]);
                if (now == 1) {
                    house.add(new int[]{i, j});
                }
                if (now == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
        arr = new int[m];
        nCm(chicken.size(), m, 0, 0);
        System.out.println(result);
    }

    private static void nCm(final int n, final int m, final int index, final int depth) {
        if (depth == m) {
            result = Math.min(calChickenDistance(), result);
            return;
        }
        for (int i = index; i < n; i++) {
            arr[depth] = i;
            nCm(n, m, i + 1, depth + 1);
        }
    }

    private static int calChickenDistance() {
        int result = 0;
        for (int i = 0; i < house.size(); i++) {
            int min = (int) 1e9;
            final int[] nowHouse = house.get(i);
            for (int j = 0; j < arr.length; j++) {
                final int[] nowChicken = chicken.get(arr[j]);
                min = Math.min(Math.abs(nowChicken[0] - nowHouse[0]) + Math.abs(nowChicken[1] - nowHouse[1]), min);
            }
            result += min;
        }
        return result;
    }
}
