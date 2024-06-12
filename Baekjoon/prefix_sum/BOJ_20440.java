package Baekjoon.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_20440 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final String[] ex = br.readLine().split(" ");
            final long e = Long.parseLong(ex[0]);
            final long m = Long.parseLong(ex[1]);
            map.put(e - 1, map.getOrDefault(e - 1, 0L) - 1);
            map.put(m - 1, map.getOrDefault(m - 1, 0L) + 1);
        }
        final List<Long> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        Long key = keys.get(keys.size() - 1);
        Long value = map.get(key);
        for (int i = keys.size() - 2; i >= 0; i--) {
            map.put(keys.get(i), value + map.get(keys.get(i)));
            key = keys.get(i);
            value = map.get(key);
        }
        long result = 0;
        int s = 0;
        int e = 0;
        for (int i = 0; i < keys.size(); i++) {
            value = map.get(keys.get(i));
            if (value > result) {
                result = value;
                s = i;
                e = i++;
                while (i < keys.size() && map.get(keys.get(i)) == result) {
                    e = i++;
                }
                i--;
            }
        }
        System.out.println(result);
        System.out.print(keys.get(s - 1) + 1);
        System.out.print(" ");
        System.out.print(keys.get(e) + 1);
    }
}
