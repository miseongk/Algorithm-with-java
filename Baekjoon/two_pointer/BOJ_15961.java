package Baekjoon.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_15961 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] ndkc = br.readLine().split(" ");
        final int n = Integer.parseInt(ndkc[0]);
        final int d = Integer.parseInt(ndkc[1]);
        final int k = Integer.parseInt(ndkc[2]);
        final int c = Integer.parseInt(ndkc[3]);
        final int[] chobab = new int[n];
        for (int i = 0; i < n; i++) {
            chobab[i] = Integer.parseInt(br.readLine());
        }

        final Set<Integer> eatChobab = new HashSet<>();
        final int[] checkChobab = new int[3001];
        int couponChobabCnt = 0;
        for (int i = 0; i < k; i++) {
            eatChobab.add(chobab[i]);
            checkChobab[chobab[i]]++;
            if (chobab[i] == c) {
                couponChobabCnt++;
            }
        }
        int start = 0;
        int end = k - 1;
        int result = 0;
        while (true) {
            result = Math.max(eatChobab.size(), result);
            if (couponChobabCnt == 0) {
                result = Math.max(eatChobab.size() + 1, result);
            }
            checkChobab[chobab[start]]--;
            if (checkChobab[chobab[start]] == 0) {
                eatChobab.remove(chobab[start]);
            }
            if (chobab[start] == c) {
                couponChobabCnt--;
            }
            start++;
            end++;
            if (end == n) {
                end = 0;
            }
            if (chobab[end] == c) {
                couponChobabCnt++;
            }
            eatChobab.add(chobab[end]);
            checkChobab[chobab[end]]++;
            if (start == n) {
                break;
            }
        }
        System.out.println(result);
    }
}
