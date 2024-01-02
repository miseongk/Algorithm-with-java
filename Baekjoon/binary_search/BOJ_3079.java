package Baekjoon.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 입국심사
public class BOJ_3079 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] nm = br.readLine().split(" ");
        final long n = Long.parseLong(nm[0]);
        final long m = Long.parseLong(nm[1]);
        final long[] t = new long[(int) n];
        long start = 0;
        long end = 0;
        for (int i = 0; i < n; i++) {
            t[i] = Long.parseLong(br.readLine());
            end = Math.max(end, t[i]);
        }
        end *= m;
        long mid = 0;
        long result = end;
        while (start <= end) {
            mid = (start + end) / 2;
            long cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += mid / t[i];
                if (cnt > m) {
                    break;
                }
            }
            if (cnt >= m) {
                end = mid - 1;
                result = Math.min(result, mid);
            } else {
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}
