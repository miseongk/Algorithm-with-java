package PGS.Level3;

public class PGS_258705 {

    // 풀이 참고함
    // https://codapul.blogspot.com/2024/01/2024-kakao-winter-internship_26.html
    public int solution(final int n, final int[] tops) {
        final int[][] dp = new int[n][2];
        if (tops[0] == 1) {
            dp[0][0] = 3;
            dp[0][1] = 1;
        } else {
            dp[0][0] = 2;
            dp[0][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (tops[i] == 1) {
                dp[i][0] = (3 * dp[i - 1][0] + 2 * dp[i - 1][1]) % 10007;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            } else {
                dp[i][0] = (2 * dp[i - 1][0] + dp[i - 1][1]) % 10007;
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            }
        }

        return (dp[n - 1][0] + dp[n - 1][1]) % 10007;
    }
}
