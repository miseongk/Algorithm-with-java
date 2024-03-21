package PGS.Level3;

import java.util.Arrays;

public class PGS_118668_2 {

    // DP를 이용한 풀이
    public int solution(int alp, int cop, final int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(problems[i][0], maxAlp);
            maxCop = Math.max(problems[i][1], maxCop);
        }
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }
        alp = Math.min(maxAlp, alp);
        cop = Math.min(maxCop, cop);
        final int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 10000);
        }
        dp[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i + 1 < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                }
                if (j + 1 < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                }
                for (int k = 0; k < problems.length; k++) {
                    final int alp_req = problems[k][0];
                    final int cop_req = problems[k][1];
                    final int alp_rwd = problems[k][2];
                    final int cop_rwd = problems[k][3];
                    final int cost = problems[k][4];
                    if (i >= alp_req && j >= cop_req) {
                        final int new_alp = Math.min(i + alp_rwd, maxAlp);
                        final int new_cop = Math.min(j + cop_rwd, maxCop);
                        dp[new_alp][new_cop] = Math.min(dp[new_alp][new_cop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
