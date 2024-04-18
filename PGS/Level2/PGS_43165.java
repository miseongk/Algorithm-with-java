package PGS.Level2;

import java.util.LinkedList;
import java.util.Queue;

public class PGS_43165 {

    public int solution(final int[] numbers, final int target) {
        return bfs(numbers, 0, target);
    }

    private int bfs(final int[] numbers, final int index, final int target) {
        int result = 0;
        final Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{numbers[index], index + 1});
        queue.add(new int[]{-numbers[index], index + 1});
        while (!queue.isEmpty()) {
            final int[] now = queue.poll();
            if (now[1] == numbers.length) {
                if (now[0] == target) {
                    result++;
                }
                continue;
            }
            queue.add(new int[]{now[0] + numbers[now[1]], now[1] + 1});
            queue.add(new int[]{now[0] - numbers[now[1]], now[1] + 1});
        }
        return result;
    }
}
