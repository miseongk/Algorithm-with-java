package Baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ_20210 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final PriorityQueue<SplitString> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            final String input = br.readLine();
            pq.add(new SplitString(input));
        }
        for (int i = 0; i < n; i++) {
            System.out.println(pq.poll().str);
        }
    }

    private static class SplitString implements Comparable<SplitString> {

        String str;
        Map<Integer, String> stringMap = new HashMap<>();

        public SplitString(final String str) {
            this.str = str;
            int i = 0;
            int j = 0;
            while (i < str.length()) {
                final char c = str.charAt(i);
                // 숫자가 나오면 숫자를 단위로 묶어서 하나의 문자 처럼 취급
                if (Character.isDigit(c)) {
                    final StringBuilder digit = new StringBuilder(String.valueOf(c));
                    i++;
                    while (i < str.length() && Character.isDigit(str.charAt(i))) {
                        digit.append(str.charAt(i));
                        i++;
                    }
                    stringMap.put(j, digit.toString());
                } else {
                    stringMap.put(j, String.valueOf(c));
                    i++;
                }
                j++;
            }
        }

        @Override
        public int compareTo(final SplitString o) {
            final int length = Math.min(this.stringMap.size(), o.stringMap.size());
            for (int i = 0; i < length; i++) {
                String thisNow = this.stringMap.get(i);
                String otherNow = o.stringMap.get(i);
                // 두 문자 중 하나만 숫자인 경우
                if (Character.isDigit(thisNow.charAt(0)) && !Character.isDigit(otherNow.charAt(0))) {
                    return -1;
                } else if (!Character.isDigit(thisNow.charAt(0)) && Character.isDigit(otherNow.charAt(0))) {
                    return 1;
                } // 두 문자 모두 숫자인 경우
                else if (Character.isDigit(thisNow.charAt(0)) && Character.isDigit(otherNow.charAt(0))) {
                    int thisZeroCnt = 0;
                    int otherZeroCnt = 0;
                    // 숫자가 0으로 시작하면 0을 제거하고 0의 개수를 저장
                    if (thisNow.charAt(0) == '0') {
                        final int j = removeZero(thisNow);
                        thisNow = thisNow.substring(j);
                        thisZeroCnt = j;
                    }
                    if (otherNow.charAt(0) == '0') {
                        final int j = removeZero(otherNow);
                        otherNow = otherNow.substring(j);
                        otherZeroCnt = j;
                    }
                    // 0을 제거한 두 숫자의 길이를 비교하여 숫자가 짧으면 작은 수 이기 때문에 -1 리턴
                    if (thisNow.length() < otherNow.length()) {
                        return -1;
                    } else if (thisNow.length() > otherNow.length()) {
                        return 1;
                    } // 두 숫자의 길이가 같은 경우
                    else {
                        // 두 숫자를 하나씩 비교하여 작은 수이면 -1 리턴
                        for (int j = 0; j < thisNow.length(); j++) {
                            if (thisNow.charAt(j) < otherNow.charAt(j)) {
                                return -1;
                            } else if (thisNow.charAt(j) > otherNow.charAt(j)) {
                                return 1;
                            }
                        }
                        // 두 숫자가 같으면 0의 길이가 짧으면 -1 리턴
                        if (thisZeroCnt < otherZeroCnt) {
                            return -1;
                        } else if (thisZeroCnt > otherZeroCnt) {
                            return 1;
                        }
                    }
                } // 두 문자 모두 알파벳인 경우
                else {
                    // AaBbCc...Zz에 맞게 정렬
                    if (thisNow.equalsIgnoreCase(otherNow)) {
                        if (thisNow.charAt(0) < otherNow.charAt(0)) {
                            return -1;
                        } else if (thisNow.charAt(0) > otherNow.charAt(0)) {
                            return 1;
                        }
                    } else {
                        if (thisNow.toLowerCase().charAt(0) < otherNow.toLowerCase().charAt(0)) {
                            return -1;
                        } else if (thisNow.toLowerCase().charAt(0) > otherNow.toLowerCase().charAt(0)) {
                            return 1;
                        }
                    }
                }
            }
            return Integer.compare(this.stringMap.size(), o.stringMap.size());
        }

        private int removeZero(final String str) {
            int i = 0;
            while (i < str.length() && str.charAt(i) == '0') {
                i++;
            }
            return i;
        }
    }
}
