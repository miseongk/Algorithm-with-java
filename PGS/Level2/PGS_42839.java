package PGS.Level2;

import java.util.HashSet;
import java.util.Set;

public class PGS_42839 {

    static int answer = 0;
    static Set<Integer> permutation = new HashSet<>();

    public int solution(final String numbers) {
        final char[] chars = numbers.toCharArray();
        final int length = chars.length;
        for (int i = 1; i <= length; i++) {
            permutation(chars, new char[length], new boolean[length], 0, length, i);
        }

        for (final Integer integer : permutation) {
            if (isPrime(integer)) {
                answer++;
            }
        }

        return answer;
    }

    public void permutation(
            final char[] arr, final char[] output, final boolean[] visited, final int depth, final int n, final int r
    ) {
        if (depth == r) {
            final StringBuilder numberStr = new StringBuilder();
            for (int i = 0; i < r; i++) {
                numberStr.append(output[i]);
            }
            final int number = Integer.parseInt(numberStr.toString());
            permutation.add(number);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    private boolean isPrime(final Integer number) {
        int cnt = 0;
        for (int i = 1; i <= number; i++) {
            if (cnt == 3) {
                return false;
            }
            if (number % 2 == 0 && number != 2) {
                return false;
            }
            if (number % i == 0) {
                cnt++;
            }
        }
        return cnt == 2;
    }
}
