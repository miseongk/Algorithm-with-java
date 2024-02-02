package Softeer.Level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SFTR_6250 {

    static StringBuilder sb = new StringBuilder();
    static int n;

    public static void main(final String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        final int[] resultScore = new int[n];
        for (int i = 0; i < 3; i++) {
            final String[] scores = br.readLine().split(" ");
            final Map<Integer, Integer> scoreMap = new HashMap<>();
            for (int j = 0; j < n; j++) {
                scoreMap.put(j, Integer.parseInt(scores[j]));
                resultScore[j] += Integer.parseInt(scores[j]);
            }
            findRank(scoreMap);
            sb.append("\n");
        }
        final Map<Integer, Integer> resultScoreMap = new HashMap<>();
        for (int j = 0; j < n; j++) {
            resultScoreMap.put(j, resultScore[j]);
        }
        findRank(resultScoreMap);
        System.out.print(sb);
    }

    private static void findRank(final Map<Integer, Integer> scoreMap) {
        final List<Integer> keySetList = new ArrayList<>(scoreMap.keySet());
        Collections.sort(keySetList, (a, b) -> scoreMap.get(b).compareTo(scoreMap.get(a)));
        final int[] rank = new int[n];
        int prevScore = -1;
        int prevCnt = 1;
        for (int j = 0; j < n; j++) {
            if (prevScore == scoreMap.get(keySetList.get(j))) {
                rank[keySetList.get(j)] = j + 1 - prevCnt;
                prevCnt++;
            } else {
                rank[keySetList.get(j)] = j + 1;
                prevCnt = 1;
            }
            prevScore = scoreMap.get(keySetList.get(j));
        }
        for (int j = 0; j < n; j++) {
            sb.append(rank[j]).append(" ");
        }
    }
}
