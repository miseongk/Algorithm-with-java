package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BOJ_18513 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        final String[] input = br.readLine().split(" ");
        Queue<long[]> queue = new LinkedList<>();
        Set<Long> house = new HashSet<>();
        for (int i = 0; i < n; i++) {
            queue.add(new long[]{Long.parseLong(input[i]), 0});
            house.add(Long.parseLong(input[i]));
        }
        long result = 0;
        int[] direction = {-1, 1};
        while (!queue.isEmpty()) {
            final long[] now = queue.poll();
            final long pos = now[0];
            final long depth = now[1];
            for (int i = 0; i < 2; i++) {
                long nextPos = pos + direction[i];
                if (!house.contains(nextPos)) {
                    house.add(nextPos);
                    result += depth + 1;
                    k--;
                    queue.add(new long[]{nextPos, depth + 1});
                }
                if (k == 0) {
                    System.out.println(result);
                    return;
                }
            }
        }
    }
}
