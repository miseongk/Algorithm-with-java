package PGS.Level1;

import java.util.HashMap;
import java.util.Map;

public class PGS_258712 {

    public int solution(final String[] friends, final String[] gifts) {
        int answer = 0;

        final Map<String, Integer> friendsIdx = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendsIdx.put(friends[i], i);
        }
        final int[][] matrix = new int[friends.length][friends.length];
        final int[] rank = new int[friends.length];
        for (int i = 0; i < gifts.length; i++) {
            final String[] fromTo = gifts[i].split(" ");
            final String from = fromTo[0];
            final String to = fromTo[1];
            matrix[friendsIdx.get(from)][friendsIdx.get(to)]++;
            rank[friendsIdx.get(from)]++;
            rank[friendsIdx.get(to)]--;
        }
        for (int i = 0; i < friends.length; i++) {
            int cnt = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) {
                    continue;
                }
                if (matrix[i][j] > matrix[j][i]) {
                    cnt++;
                } else if (matrix[i][j] == matrix[j][i]) {
                    if (rank[i] > rank[j]) {
                        cnt++;
                    }
                }
            }
            answer = Math.max(cnt, answer);
        }

        return answer;
    }
}
