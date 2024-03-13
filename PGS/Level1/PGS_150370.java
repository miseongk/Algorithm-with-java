package PGS.Level1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGS_150370 {

    int todayY;
    int todayM;
    int todayD;

    public int[] solution(final String today, final String[] terms, final String[] privacies) {
        final List<Integer> answer = new ArrayList<>();
        String[] splitDate = today.split("\\.");
        todayY = Integer.parseInt(splitDate[0]);
        todayM = Integer.parseInt(splitDate[1]);
        todayD = Integer.parseInt(splitDate[2]);

        final Map<String, Integer> kinds = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            final String[] split = terms[i].split(" ");
            kinds.put(split[0], Integer.parseInt(split[1]));
        }
        for (int i = 0; i < privacies.length; i++) {
            final String[] split = privacies[i].split(" ");
            final String date = split[0];
            final String kind = split[1];
            splitDate = date.split("\\.");
            final int year = Integer.parseInt(splitDate[0]);
            final int month = Integer.parseInt(splitDate[1]);
            final int day = Integer.parseInt(splitDate[2]);
            final int term = kinds.get(kind);
            final int[] ymd = addTerm(year, month, day, term);
            if (compareDate(ymd)) {
                answer.add(i + 1);
            }
        }
        final int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }

    private int[] addTerm(int year, int month, final int day, int term) {
        year += term / 12;
        term = term % 12;
        if (month + term > 12) {
            year++;
            month += term - 12;
        } else {
            month += term;
        }
        return new int[]{year, month, day};
    }

    private boolean compareDate(final int[] ymd) {
        final int year = ymd[0];
        final int month = ymd[1];
        final int day = ymd[2];
        if (todayY > year) {
            return true;
        } else if (todayY == year) {
            if (todayM > month) {
                return true;
            } else if (todayM == month) {
                if (todayD >= day) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
