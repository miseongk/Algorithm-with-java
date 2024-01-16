package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_11000 {

    static long[][] lecture;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        lecture = new long[n][2];
        for (int i = 0; i < n; i++) {
            final String[] st = br.readLine().split(" ");
            lecture[i][0] = Long.parseLong(st[0]);
            lecture[i][1] = Long.parseLong(st[1]);
        }

        Arrays.sort(lecture, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return (int) (o1[1] - o2[1]);
            }
            return (int) (o1[0] - o2[0]);
        });

        final PriorityQueue<Long> queue = new PriorityQueue<>();

        queue.offer(lecture[0][1]);
        for (int i = 1; i < n; i++) {
            final Long top = queue.peek();
            if (top <= lecture[i][0]) {
                queue.poll();
            }
            queue.offer(lecture[i][1]);
        }

        System.out.println(queue.size());
    }
}
