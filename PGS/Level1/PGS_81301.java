package PGS.Level1;

import java.util.HashMap;
import java.util.Map;

// 숫자 문자열과 영단어
// 배열과 replaceAll을 사용해서 반복문 10번으로 가능
public class PGS_81301 {

    public int solution(final String s) {
        final Map<String, String> map = new HashMap<>();
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");

        final StringBuilder result = new StringBuilder();
        StringBuilder alphabet = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            final char curChar = s.charAt(i);
            if (Character.isDigit(curChar)) {
                result.append(curChar);
            } else {
                alphabet.append(curChar);
                if (map.get(alphabet.toString()) != null) {
                    result.append(map.get(alphabet.toString()));
                    alphabet = new StringBuilder();
                }
            }
        }

        return Integer.parseInt(result.toString());
    }

    public static void main(final String[] args) {
        final int result = new PGS_81301().solution("one4seveneight");
        System.out.println(result);
    }
}
