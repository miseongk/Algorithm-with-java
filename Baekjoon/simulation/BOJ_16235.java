package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_16235 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nmk = br.readLine().split(" ");
        final int n = Integer.parseInt(nmk[0]);
        final int m = Integer.parseInt(nmk[1]);
        final int k = Integer.parseInt(nmk[2]);
        final int[][] a = new int[n][n];
        final int[][] feed = new int[n][n];
        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(input[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(feed[i], 5);
        }
        final PriorityQueue<Tree> trees = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            final String[] input = br.readLine().split(" ");
            trees.add(new Tree(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1,
                    Integer.parseInt(input[2])));
        }
        final PriorityQueue<Tree> tmp = new PriorityQueue<>();
        final Queue<Tree> deadTrees = new LinkedList<>();
        final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < k; i++) {
            // 봄
            while (!trees.isEmpty()) {
                final Tree now = trees.poll();
                if (feed[now.x][now.y] >= now.age) {
                    feed[now.x][now.y] -= now.age;
                    tmp.add(new Tree(now.x, now.y, now.age + 1));
                } else {
                    deadTrees.add(now);
                }
            }
            // 여름
            while (!deadTrees.isEmpty()) {
                final Tree now = deadTrees.poll();
                feed[now.x][now.y] += now.age / 2;
            }
            // 가을
            while (!tmp.isEmpty()) {
                final Tree now = tmp.poll();
                if (now.age % 5 == 0) {
                    for (int j = 0; j < 8; j++) {
                        final int nx = dx[j] + now.x;
                        final int ny = dy[j] + now.y;
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            continue;
                        }
                        trees.add(new Tree(nx, ny, 1));
                    }
                }
                trees.add(now);
            }
            // 겨울
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    feed[j][l] += a[j][l];
                }
            }
        }
        System.out.println(trees.size());
    }

    static class Tree implements Comparable<Tree> {

        int x;
        int y;
        int age;

        public Tree(final int x, final int y, final int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(final Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
}
