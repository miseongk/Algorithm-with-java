package PGS.Level2;

import java.util.LinkedList;
import java.util.Queue;

public class PGS_42587 {

    public int solution(final int[] priorities, final int location) {
        int answer = 0;

        final Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new int[]{i, priorities[i]});
        }

        final Queue<int[]> order = new LinkedList<>();
        boolean flag = false;
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final int[] next = queue.poll();
                queue.add(next);
                if (now[1] < next[1]) {
                    flag = true;
                }
            }
            if (!flag) {
                order.add(now);
            } else {
                queue.add(now);
            }
            flag = false;
        }

        final int size = order.size();
        for (int i = 0; i < size; i++) {
            final int[] now = order.poll();
            if (now[0] == location) {
                answer = i + 1;
                break;
            }
        }
        return answer;
    }
}
