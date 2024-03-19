package PGS.Level1;

import java.util.HashMap;
import java.util.Map;

public class PGS_118666 {

    public String solution(final String[] survey, final int[] choices) {
        String answer = "";

        final Map<Integer, Integer> scores = new HashMap<>();
        scores.put(1, 3);
        scores.put(2, 2);
        scores.put(3, 1);
        scores.put(4, 0);
        scores.put(5, 1);
        scores.put(6, 2);
        scores.put(7, 3);

        final Map<String, Integer> types = new HashMap<>();
        types.put("R", 0);
        types.put("T", 0);
        types.put("C", 0);
        types.put("F", 0);
        types.put("J", 0);
        types.put("M", 0);
        types.put("A", 0);
        types.put("N", 0);

        for (int i = 0; i < survey.length; i++) {
            final String first = String.valueOf(survey[i].charAt(0));
            final String second = String.valueOf(survey[i].charAt(1));
            if (choices[i] <= 4) {
                types.put(first, types.get(first) + scores.get(choices[i]));
            } else {
                types.put(second, types.get(second) + scores.get(choices[i]));
            }
        }
        if (types.get("R") >= types.get("T")) {
            answer += "R";
        } else {
            answer += "T";
        }
        if (types.get("C") >= types.get("F")) {
            answer += "C";
        } else {
            answer += "F";
        }
        if (types.get("J") >= types.get("M")) {
            answer += "J";
        } else {
            answer += "M";
        }
        if (types.get("A") >= types.get("N")) {
            answer += "A";
        } else {
            answer += "N";
        }

        return answer;
    }
}
