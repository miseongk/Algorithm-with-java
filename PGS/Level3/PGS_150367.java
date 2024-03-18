package PGS.Level3;

import java.util.Stack;

public class PGS_150367 {

    boolean flag = false;
    int result = 1;

    public int[] solution(final long[] numbers) {
        final int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = makeBinary(numbers[i]);
            final int size = binary.length();
            binary = fillZero(size, binary);
            check(binary, '1');
            answer[i] = result;
            result = 1;
            flag = false;
        }

        return answer;
    }

    private String makeBinary(long number) {
        final Stack<Long> stack = new Stack<>();
        while (number > 0) {
            stack.add(number % 2);
            number = number / 2;
        }
        final int size = stack.size();
        String binary = "";
        for (int i = 0; i < size; i++) {
            binary += stack.pop();
        }
        if (size % 2 == 0) {
            binary = "0" + binary;
        }

        return binary;
    }

    private String fillZero(int size, final String binary) {
        size++;
        int num = 1;
        while (true) {
            num *= 2;
            if (num == size) {
                return binary;
            }
            if (num > size) {
                return new String(new char[num - size]).replace("\0", "0") + binary;
            }
        }
    }

    private void check(final String binary, char parent) {
        if (flag) {
            return;
        }
        final int size = binary.length();
        final int mid = size / 2;
        if (mid == 0) {
            return;
        }
        if (binary.charAt(mid) == '0') {
            parent = '0';
        }
        if (mid > 1) {
            check(binary.substring(0, mid), parent);
            check(binary.substring(mid + 1), parent);
        } else if (mid == 1) {
            if (parent == '0') {
                if (!binary.equals("000")) {
                    result = 0;
                    flag = true;
                    return;
                }
            }
        }
    }
}
