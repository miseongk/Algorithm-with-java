package PGS.Level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PGS_258709 {

    int[] arr;
    int[] bArr;
    int diceLength;
    List<Integer> A = new ArrayList<>();
    List<Integer> B = new ArrayList<>();
    int maxWinCnt = 0;
    boolean flag = false;
    int[] answer;

    public int[] solution(final int[][] dice) {
        diceLength = dice.length;
        answer = new int[diceLength / 2];
        arr = new int[diceLength / 2];
        bArr = new int[diceLength / 2];
        pickDice(0, 0, diceLength / 2, dice);

        return answer;
    }

    private void pickDice(final int depth, final int start, final int m, final int[][] dice) {
        if (depth == m) {
            makeBArr();
            findCase(dice, 0, 0, 0);
            findWinCnt();
            if (flag) {
                for (int i = 0; i < diceLength / 2; i++) {
                    answer[i] = arr[i] + 1;
                }
            }
            A = new ArrayList<>();
            B = new ArrayList<>();
            flag = false;
            return;
        }
        for (int i = start; i < m * 2; i++) {
            arr[depth] = i;
            pickDice(depth + 1, i + 1, m, dice);
        }
    }

    private void makeBArr() {
        final Set<Integer> idx = new HashSet<>();
        for (int i = 0; i < diceLength; i++) {
            idx.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            idx.remove(arr[i]);
        }
        int j = 0;
        for (final Integer i : idx) {
            bArr[j] = i;
            j++;
        }
    }

    private void findCase(final int[][] dice, final int depth, int aSum, int bSum) {
        if (depth == arr.length) {
            A.add(aSum);
            B.add(bSum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            aSum += dice[arr[depth]][i];
            bSum += dice[bArr[depth]][i];
            findCase(dice, depth + 1, aSum, bSum);
            aSum -= dice[arr[depth]][i];
            bSum -= dice[bArr[depth]][i];
        }
    }

    private void findWinCnt() {
        int win = 0;
        Collections.sort(B);

        for (int i = 0; i < A.size(); i++) {
            final int target = A.get(i);
            int start = 0;
            int end = B.size() - 1;
            int mid = 0;
            while (start <= end) {
                mid = (start + end) / 2;
                if (B.get(mid) < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            win += end + 1;
        }
        if (maxWinCnt < win) {
            maxWinCnt = win;
            flag = true;
        }
    }
}

