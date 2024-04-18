package PGS.Level2;

public class PGS_42842 {

    public int[] solution(final int brown, final int yellow) {
        int[] answer = {};

        for (int i = 1; i <= Math.sqrt(yellow); i++) {
            if (yellow % i == 0) {
                final int j = yellow / i;
                if (i * 2 + j * 2 + 4 == brown) {
                    answer = new int[]{j + 2, i + 2};
                    break;
                }
            }
        }
        return answer;
    }
}
