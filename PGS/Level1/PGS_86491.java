package PGS.Level1;

public class PGS_86491 {

    public int solution(int[][] sizes) {
        int left_max = 0;
        int right_max = 0;

        for (int[] size : sizes) {
            left_max = Math.max(left_max, Math.max(size[0], size[1]));
            right_max = Math.max(right_max, Math.min(size[0], size[1]));
        }

        return left_max * right_max;
    }
}
