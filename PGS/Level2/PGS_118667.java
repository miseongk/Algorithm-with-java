package PGS.Level2;

public class PGS_118667 {

    public int solution(final int[] queue1, final int[] queue2) {
        int answer = 600001;
        final int len = queue1.length;

        final long[] sum = new long[len * 2 + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + queue1[i - 1];
        }
        for (int i = len + 1; i <= len * 2; i++) {
            sum[i] = sum[i - 1] + queue2[i - 1 - len];
        }
        if (sum[len * 2] - sum[len] == sum[len] - sum[0]) {
            return 0;
        }

        final long toBe = sum[len * 2] / 2;
        int start = 1;
        int end = 1;
        long cur = 0;
        int cnt = 0;
        while (end <= len * 2) {
            cur = sum[end] - sum[start - 1];

            if (cur == toBe) {
                if (start <= len && end <= len) {
                    if (start == end) {
                        cnt = len * 2 - 1;
                    } else if (end == len) {
                        cnt = start - 1;
                    } else if (start == 1) {
                        cnt = end + len;
                    } else {
                        cnt = len * 2;
                    }
                } else if (start <= len && end > len) {
                    cnt = start - 1 + end - len;
                } else {
                    if (start == end) {
                        cnt = end + end - len - 1;
                    } else if (end == len * 2) {
                        cnt = start - len - 1;
                    } else if (start == len + 1) {
                        cnt = end;
                    } else {
                        cnt = len * 2;
                    }
                }
                answer = Math.min(cnt, answer);
                start++;
            } else if (cur > toBe) {
                start++;
            } else {
                end++;
            }
        }

        if (answer == 600001) {
            return -1;
        }

        return answer;
    }
}
