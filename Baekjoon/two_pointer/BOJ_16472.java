package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        final String str = br.readLine();
        int[] alphabetCnt = new int[26];
        int nowAlphabetCnt = 0;
        int maxCnt = 0;
        int start = 0;
        int end = 0;
        while (end < str.length()) {
            final char endAlphabet = str.charAt(end);
            if (alphabetCnt[endAlphabet - 'a'] == 0) {
                nowAlphabetCnt++;
            }
            alphabetCnt[endAlphabet - 'a']++;
            end++;

            while (nowAlphabetCnt > n) {
                final char startAlphabet = str.charAt(start);
                alphabetCnt[startAlphabet - 'a']--;
                if (alphabetCnt[startAlphabet - 'a'] == 0) {
                    nowAlphabetCnt--;
                }
                start++;
            }

            maxCnt = Math.max(end - start, maxCnt);
        }
        System.out.println(maxCnt);
    }
}
