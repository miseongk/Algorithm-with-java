package PGS.Level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PGS_42586 {

    public int[] solution(final int[] progresses, final int[] speeds) {
        int[] answer = {};

        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            final int remain = (int) Math.ceil((100 - progresses[i]) / (speeds[i] / 1.0));
            queue.add(remain);
        }

        final List<Integer> result = new ArrayList<>();
        int day = queue.poll();
        int cnt = 1;
        while (!queue.isEmpty()) {
            final int next = queue.poll();
            if (next > day) {
                day = next;
                result.add(cnt);
                cnt = 1;
            } else {
                cnt++;
            }
        }
        result.add(cnt);
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
