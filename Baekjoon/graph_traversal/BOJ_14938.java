package Baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_14938 {

    static int n;
    static int m;
    static int[] items;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nmr = br.readLine().split(" ");
        n = Integer.parseInt(nmr[0]);
        m = Integer.parseInt(nmr[1]);
        final int r = Integer.parseInt(nmr[2]);
        items = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            final String[] input = br.readLine().split(" ");
            final int x = Integer.parseInt(input[0]) - 1;
            final int y = Integer.parseInt(input[1]) - 1;
            final int distance = Integer.parseInt(input[2]);
            graph.get(x).add(new Node(y, distance, m));
            graph.get(y).add(new Node(x, distance, m));
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            final int cnt = getItems(i);
            result = Math.max(cnt, result);
        }
        System.out.println(result);
    }

    private static int getItems(final int start) {
        int cnt = 0;
        final int[] tmpItems = Arrays.copyOf(items, n);
        final Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0, m));
        cnt += tmpItems[start];
        tmpItems[start] = 0;
        while (!queue.isEmpty()) {
            final Node now = queue.poll();
            final int pos = now.getOther();
            final int remainRange = now.getRemainRange();
            for (int i = 0; i < graph.get(pos).size(); i++) {
                final int cost = graph.get(pos).get(i).getDistance();
                final int next = graph.get(pos).get(i).getOther();
                if (cost > remainRange) {
                    continue;
                }
                cnt += tmpItems[next];
                tmpItems[next] = 0;
                queue.offer(new Node(next, 0, remainRange - cost));
            }
        }
        return cnt;
    }

    private static class Node {

        int other;
        int distance;
        int remainRange;

        public Node(final int other, final int distance, final int remainRange) {
            this.other = other;
            this.distance = distance;
            this.remainRange = remainRange;
        }

        public int getOther() {
            return other;
        }

        public int getDistance() {
            return distance;
        }

        public int getRemainRange() {
            return remainRange;
        }
    }
}
