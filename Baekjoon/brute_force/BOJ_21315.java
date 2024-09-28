package Baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_21315 {

    static int n;

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        final int[] cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int e = 0;
        int tmpN = n - 1;
        while (tmpN > 0) {
            tmpN /= 2;
            if (tmpN > 0) {
                e++;
            }
        }
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e; j++) {
                final LinkedList<Integer> arr = new LinkedList<>();
                for (int k = 1; k <= n; k++) {
                    arr.add(k);
                }
                final LinkedList<Integer> tmp = shuffle(arr, i);
                final LinkedList<Integer> result = shuffle(tmp, j);
                if (match(result, cards)) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
    }

    private static LinkedList<Integer> shuffle(final LinkedList<Integer> arr, final int k) {
        if (k == 0) {
            return new LinkedList<>() {{
                add(arr.get(1));
                add(arr.get(0));
            }};
        }
        final LinkedList<Integer> copyArr = new LinkedList<>(arr);
        final LinkedList<Integer> result = new LinkedList<>();
        final LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= (int) Math.pow(2, k); i++) {
            tmp.addFirst(copyArr.removeLast());
        }
        while (!copyArr.isEmpty()) {
            result.addFirst(copyArr.removeLast());
        }
        final LinkedList<Integer> r = new LinkedList<>(shuffle(tmp, k - 1));
        while (!r.isEmpty()) {
            result.addFirst(r.removeLast());
        }
        return result;
    }

    private static boolean match(final List<Integer> result, final int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            if (result.get(i) != cards[i]) {
                return false;
            }
        }
        return true;
    }
}
