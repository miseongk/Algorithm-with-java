package PGS.Level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PGS_118668_1 {

    public int solution(final int alp, final int cop, final int[][] problems) {
        // cost, [alp_req, cop_req, alp_rwd, cop_rwd]
        final Map<Integer, List<int[]>> study = new HashMap<>();
        // curTime, "alp cop"
        final Map<Integer, Set<String>> dp = new HashMap<>();
        int maxAlp = 0;
        int maxCop = 0;
        List<int[]> s = new ArrayList<>();
        s.add(new int[]{0, 0, 1, 0});
        s.add(new int[]{0, 0, 0, 1});
        study.put(1, s);
        for (int i = 0; i < problems.length; i++) {
            s = study.getOrDefault(problems[i][4], new ArrayList<>());
            s.add(new int[]{problems[i][0], problems[i][1], problems[i][2], problems[i][3]});
            study.put(problems[i][4], s);
            maxAlp = Math.max(problems[i][0], maxAlp);
            maxCop = Math.max(problems[i][1], maxCop);
        }
        int maxTime = maxAlp - alp + maxCop - cop;
        if (maxAlp <= alp && maxCop <= cop) {
            return 0;
        }
        if (maxAlp <= alp && maxCop > cop) {
            maxTime = maxCop - cop;
        } else if (maxAlp > alp && maxCop <= cop) {
            maxTime = maxAlp - alp;
        }
        for (int i = 0; i <= maxTime; i++) {
            dp.put(i, new HashSet<>());
        }
        dp.get(0).add(new String(alp + " " + cop));
        for (int i = 1; i <= maxTime; i++) {
            for (int j = 1; j <= i; j++) {
                if (study.getOrDefault(j, new ArrayList<>()).isEmpty()) {
                    continue;
                }
                final List<int[]> canStudy = study.get(j);
                for (final String before : dp.get(i - j)) {
                    final int beforeAlp = Integer.parseInt(before.split(" ")[0]);
                    final int beforeCop = Integer.parseInt(before.split(" ")[1]);
                    for (int k = 0; k < canStudy.size(); k++) {
                        final int[] now = canStudy.get(k);
                        final int upAlp = now[2];
                        final int upCop = now[3];
                        if (beforeAlp >= now[0] && beforeCop >= now[1]) {
                            final int nextAlp = beforeAlp + upAlp;
                            final int nextCop = beforeCop + upCop;
                            if (nextAlp >= maxAlp && nextCop >= maxCop) {
                                return i;
                            }
                            dp.get(i).add(new String(nextAlp + " " + nextCop));
                        }
                    }
                }
            }
        }

        return maxTime;
    }
}
