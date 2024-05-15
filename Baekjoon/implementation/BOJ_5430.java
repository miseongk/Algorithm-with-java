package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5430 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final String command = br.readLine();
            final int n = Integer.parseInt(br.readLine());
            String sArr = br.readLine();
            if (n == 0) {
                if (command.contains("D")) {
                    sb.append("error\n");
                } else {
                    sb.append("[]\n");
                }
                continue;
            }
            sArr = sArr.substring(1, sArr.length() - 1);
            final int[] arr = Arrays.stream(sArr.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int l = 0;
            int r = arr.length - 1;
            boolean rightDirect = true;
            boolean error = false;
            for (int j = 0; j < command.length(); j++) {
                final char c = command.charAt(j);
                if (c == 'R') {
                    rightDirect = !rightDirect;
                } else {
                    if (rightDirect) {
                        l++;
                    } else {
                        r--;
                    }
                    if (l - r == 2) {
                        error = true;
                        break;
                    }
                }
            }
            if (error) {
                sb.append("error\n");
                continue;
            }
            sb.append("[");
            if (rightDirect) {
                for (int j = l; j <= r; j++) {
                    sb.append(arr[j]);
                    if (j != r) {
                        sb.append(",");
                    }
                }
            } else {
                for (int j = r; j >= l; j--) {
                    sb.append(arr[j]);
                    if (j != l) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]\n");
        }
        System.out.print(sb);
    }
}
