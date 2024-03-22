package PGS.Level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PGS_42577 {

    public boolean solution(final String[] phone_book) {
        Arrays.sort(phone_book, (a, b) -> {
            if (a.length() > b.length()) {
                return 1;
            } else if (a.length() == b.length()) {
                return 0;
            } else {
                return -1;
            }
        });

        final Set<String> nums = new HashSet<>();
        nums.add(phone_book[0]);
        for (int i = 1; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                if (nums.contains(phone_book[i].substring(0, j))) {
                    return false;
                }
            }
            nums.add(phone_book[i]);
        }
        return true;
    }
}
