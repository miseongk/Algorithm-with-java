package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_19598 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());

        final PriorityQueue<Time> pq = new PriorityQueue<>();
        final List<Time> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final String[] se = br.readLine().split(" ");
            final int start = Integer.parseInt(se[0]);
            final int end = Integer.parseInt(se[1]);
            times.add(new Time(start, end));
        }
        Collections.sort(times, Comparator.comparingInt(a -> a.start));
        pq.add(times.get(0));
        for (int i = 1; i < n; i++) {
            final Time now = pq.peek();
            if (now.end <= times.get(i).start) {
                pq.poll();
            }
            pq.add(times.get(i));
        }
        System.out.println(pq.size());
    }

    static class Time implements Comparable<Time> {

        int start;
        int end;

        public Time(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(final Time o) {
            return Integer.compare(this.end, o.end);
        }
    }
}
