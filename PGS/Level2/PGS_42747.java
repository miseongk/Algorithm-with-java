package PGS.Level2;

import java.util.Arrays;

public class PGS_42747 {

    public int solution(final int[] citations) {
        Arrays.sort(citations);
        int h = 1;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= h) {
                h++;
            } else {
                break;
            }
        }
        return h - 1;
    }
}
