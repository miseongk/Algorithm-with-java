package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class BOJ_13975 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final int k = Integer.parseInt(br.readLine());
            final List<Long> files = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .sorted()
                    .boxed()
                    .collect(Collectors.toList());
            final PriorityQueue<Long> pq = new PriorityQueue<>(files);
            long result = 0;
            while (!pq.isEmpty()) {
                if (pq.size() == 1) {
                    break;
                }
                final Long first = pq.poll();
                final Long second = pq.poll();
                result += first + second;
                pq.add(first + second);
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
