package Baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀로 풀면 더 깔끔함
public class BOJ_17609 {

    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final String str = br.readLine();
            int result = rightWay(str);
            if (result == 2) {
                result = leftWay(str);
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static int rightWay(final String str) {
        int start = 0;
        int end = str.length() - 1;
        boolean flag = false;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                if (!flag) {
                    if (str.charAt(start + 1) == str.charAt(end)) {
                        flag = true;
                        start += 2;
                        end--;
                    } else {
                        return 2;
                    }
                } else {
                    return 2;
                }
            } else {
                start++;
                end--;
            }
        }
        if (flag) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int leftWay(final String str) {
        int start = 0;
        int end = str.length() - 1;
        boolean flag = false;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                if (!flag) {
                    if (str.charAt(start) == str.charAt(end - 1)) {
                        flag = true;
                        start++;
                        end -= 2;
                    } else {
                        return 2;
                    }
                } else {
                    return 2;
                }
            } else {
                start++;
                end--;
            }
        }
        if (flag) {
            return 1;
        } else {
            return 0;
        }
    }
}
