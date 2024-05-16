package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(Long.valueOf(br.readLine()));
        }
        long sum = 0;
        if (n == 1) {
            System.out.println(sum);
            return;
        }
        while (pq.size() >= 2) {
            final Long a = pq.poll();
            final Long b = pq.poll();
            sum += (a + b);
            pq.add(a + b);

        }
        System.out.println(sum);
    }
}
