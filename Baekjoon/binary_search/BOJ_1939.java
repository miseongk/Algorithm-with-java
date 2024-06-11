package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_1939 {

    static List<List<Bridge>> island = new ArrayList<>();
    static boolean[] visited;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);
        for (int i = 0; i <= n; i++) {
            island.add(new ArrayList<>());
        }
        long left = 0;
        long right = 0;
        for (int i = 0; i < m; i++) {
            final String[] abc = br.readLine().split(" ");
            final int a = Integer.parseInt(abc[0]);
            final int b = Integer.parseInt(abc[1]);
            final long c = Long.parseLong(abc[2]);
            island.get(a).add(new Bridge(b, c));
            island.get(b).add(new Bridge(a, c));
            right = Math.max(c, right);
        }
        final String[] ab = br.readLine().split(" ");
        final int a = Integer.parseInt(ab[0]);
        final int b = Integer.parseInt(ab[1]);
        long result = 0;
        while (left <= right) {
            visited = new boolean[n + 1];
            final long mid = (left + right) / 2;
            if (bfs(mid, a, b)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    private static boolean bfs(final long maxWeight, final int start, final int end) {
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            final Integer now = queue.poll();
            for (int i = 0; i < island.get(now).size(); i++) {
                final Bridge next = island.get(now).get(i);
                if (!visited[next.num] && next.dist >= maxWeight) {
                    queue.add(next.num);
                    visited[next.num] = true;
                }
            }
        }
        return visited[end];
    }

    static class Bridge {

        int num;
        long dist;

        public Bridge(final int num, final long dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}
