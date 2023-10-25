package PGS.Level1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PGS_67256 {

    static List<Integer> left = List.of(1, 4, 7);
    static List<Integer> right = List.of(3, 6, 9);
    static Map<String, int[]> position = initPosition();

    public String solution(final int[] numbers, final String hand) {
        final StringBuilder answer = new StringBuilder();
        final int[] leftCurrentPos = {3, 0};
        final int[] rightCurrentPos = {3, 2};

        for (final int number : numbers) {
            final String numberStr = String.valueOf(number);
            if (left.contains(number)) {
                answer.append("L");
                changePos(leftCurrentPos, position.get(numberStr));
                continue;
            }
            if (right.contains(number)) {
                answer.append("R");
                changePos(rightCurrentPos, position.get(numberStr));
                continue;
            }
            final int[] toGoPos = {position.get(numberStr)[0], position.get(numberStr)[1]};
            final int leftDist = Math.abs(toGoPos[0] - leftCurrentPos[0]) + Math.abs(toGoPos[1] - leftCurrentPos[1]);
            final int rightDist = Math.abs(toGoPos[0] - rightCurrentPos[0]) + Math.abs(toGoPos[1] - rightCurrentPos[1]);
            if (leftDist > rightDist) {
                answer.append("R");
                changePos(rightCurrentPos, toGoPos);
            } else if (leftDist < rightDist) {
                answer.append("L");
                changePos(leftCurrentPos, toGoPos);
            } else {
                if (Objects.equals(hand, "right")) {
                    answer.append("R");
                    changePos(rightCurrentPos, toGoPos);
                } else {
                    answer.append("L");
                    changePos(leftCurrentPos, toGoPos);
                }
            }
        }

        return answer.toString();
    }

    public void changePos(final int[] original, final int[] to) {
        original[0] = to[0];
        original[1] = to[1];
    }

    public static Map<String, int[]> initPosition() {
        final String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};
        final Map<String, int[]> position = new HashMap<>();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                position.put(keys[k], new int[]{i, j});
                k++;
            }
        }
        return position;
    }

    public static void main(final String[] args) {
        final String result = new PGS_67256().solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right");
        System.out.println(result);
    }
}
