package PGS.Level2;

import java.util.Arrays;

public class PGS_42746 {

    public String solution(final int[] numbers) {
        String answer = "";

        final String[] numStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(numStr, (a, b) -> {
            if (Integer.parseInt(a + b) > Integer.parseInt(b + a)) {
                return 1;
            } else if (Integer.parseInt(a + b) == Integer.parseInt(b + a)) {
                return 0;
            } else {
                return -1;
            }
        });
        for (int i = numbers.length - 1; i >= 0; i--) {
            answer += numStr[i];
        }

        int i = 0;
        for (; i < answer.length() - 1; i++) {
            if (answer.charAt(i) != '0') {
                break;
            }
        }

        return answer.substring(i);
    }
}
