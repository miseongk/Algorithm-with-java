package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ_2503 {

    static Set<String> canNumber = new HashSet<>();

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final String[] inputs = br.readLine().split(" ");
            final String answer = inputs[0];
            final int strike = Integer.parseInt(inputs[1]);
            final int ball = Integer.parseInt(inputs[2]);
            if (strike == 3) {
                System.out.println(1);
                return;
            }
            answers.add(new Answer(answer, strike, ball));
        }

        initNumbers();
        for (int i = 0; i < n; i++) {
            final String number = answers.get(i).getNumber();
            final int strike = answers.get(i).getStrike();
            final int ball = answers.get(i).getBall();
            removeNotMatchNumber(number, strike, ball);
        }
        System.out.println(canNumber.size());
    }

    private static void initNumbers() {
        final int[] arr = new int[3];
        final boolean[] visited = new boolean[10];
        dfs(0, arr, visited);
    }

    private static void dfs(final int depth, final int[] arr, final boolean[] visited) {
        if (depth == 3) {
            canNumber.add(String.valueOf(arr[0]) + String.valueOf(arr[1]) + String.valueOf(arr[2]));
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            arr[depth] = i;
            dfs(depth + 1, arr, visited);
            visited[i] = false;
        }
    }

    private static void removeNotMatchNumber(final String number, final int strike, final int ball) {
        final List<String> removed = new ArrayList<>();
        for (final String str : canNumber) {
            final int[] cnt = calStrikeAndBall(str, number, strike, ball);
            if (cnt[0] != strike || cnt[1] != ball) {
                removed.add(str);
            }
        }
        canNumber.removeAll(removed);
    }

    private static int[] calStrikeAndBall(final String answer, final String number, final int strike, final int ball) {
        final int[] cnt = new int[2];
        int strikeCnt = 0;
        int ballCnt = 0;
        for (int i = 0; i < 3; i++) {
            final char cur = number.charAt(i);
            for (int j = 0; j < 3; j++) {
                if (cur == answer.charAt(j)) {
                    if (i == j) {
                        strikeCnt++;
                    } else {
                        ballCnt++;
                    }
                    break;
                }
            }
        }
        cnt[0] = strikeCnt;
        cnt[1] = ballCnt;
        return cnt;
    }

}

class Answer implements Comparable<Answer> {

    String number;
    int strike;
    int ball;

    public Answer(final String number, final int strike, final int ball) {
        this.number = number;
        this.strike = strike;
        this.ball = ball;
    }

    @Override
    public int compareTo(final Answer o) {
        if (this.strike > o.strike) {
            return 1;
        } else if (this.strike == o.strike) {
            return Integer.compare(this.ball, o.ball);
        } else {
            return -1;
        }
    }

    public String getNumber() {
        return number;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
