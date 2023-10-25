package PGS.Level1;

public class PGS_147355 {

    public int solution(final String t, final String p) {
        int answer = 0;

        final int pLength = p.length();
        int start = 0;
        int end = pLength - 1;

        while (end < t.length()) {
            int flag = 0;
            final String substring = t.substring(start, end + 1);
            for (int i = 0; i < pLength; i++) {
                if (p.charAt(i) < substring.charAt(i)) {
                    flag = 0;
                    start++;
                    end++;
                    break;
                } else if (p.charAt(i) == substring.charAt(i)) {
                    flag = 1;
                } else {
                    flag = 0;
                    start++;
                    end++;
                    answer++;
                    break;
                }
            }
            if (flag == 1) {
                start++;
                end++;
                answer++;
            }
        }

        return answer;
    }

    public static void main(final String[] args) {
        final int answer = new PGS_147355().solution("3141592", "271");
        System.out.println(answer);
    }
}
