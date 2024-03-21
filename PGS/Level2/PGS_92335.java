package PGS.Level2;

public class PGS_92335 {

    public int solution(final int n, final int k) {
        int answer = 0;

        final String convert = change(n, k);
        final String[] nums = convert.split("0");
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].equals("")) {
                continue;
            }
            if (isPrime(Long.parseLong(nums[i]))) {
                answer++;
            }
        }

        return answer;
    }

    private String change(int n, final int k) {
        String result = "";
        while (n > 0) {
            result = n % k + result;
            n = n / k;
        }
        return result;
    }

    private boolean isPrime(final long num) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        final double pow = Math.pow(num, 0.5);
        int mod = 2;
        while (mod <= pow) {
            if (num % mod == 0) {
                return false;
            }
            mod++;
        }
        return true;
    }
}
