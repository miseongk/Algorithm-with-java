package PGS.Level3;

import java.util.HashSet;
import java.util.Set;

public class PGS_258707 {

    int toBe = 0;

    public int solution(final int coin, final int[] cards) {
        toBe = cards.length + 1;
        final Set<Integer> mine = new HashSet<>();
        final Set<Integer> need = new HashSet<>();
        for (int i = 0; i < cards.length / 3; i++) {
            mine.add(cards[i]);
        }
        for (int i = 0; i < cards.length / 3; i++) {
            if (mine.contains(toBe - cards[i])) {
                need.remove(cards[i]);
            } else {
                need.add(toBe - cards[i]);
            }
        }
        // 현재 카드를 낼 수 있는 횟수
        final int can = (cards.length / 3 - need.size()) / 2;

        return game(can, coin, need, cards);
    }

    private int game(int can, int coin, final Set<Integer> need, final int[] cards) {
        final Set<Integer> before = new HashSet<>();
        int round = 0;
        boolean flag = false;

        for (int i = cards.length / 3; i <= cards.length; i += 2) {
            if (i == cards.length) {
                round++;
                break;
            }
            round++;
            flag = false;
            final int one = cards[i];
            final int two = cards[i + 1];
            // 카드를 낼 수 있으면 무조건 내고 넘어가기
            if (can > 0) {
                can--;
                before.add(one);
                before.add(two);
            } else { // 카드를 낼 수 없는 경우
                // 코인 1개만 써서 카드를 낼 수 있는 경우를 먼저 생각하기
                if (coin >= 1) {
                    // 첫번째 카드와 현재 갖고 있는 카드를 합쳐서 낼 수 있는 경우
                    if (need.contains(one)) {
                        coin--;
                        need.remove(one);
                        before.add(two);
                        continue;
                    }
                    // 두번째 카드와 현재 갖고 있는 카드를 합쳐서 낼 수 있는 경우
                    if (need.contains(two)) {
                        coin--;
                        need.remove(two);
                        before.add(one);
                        continue;
                    }
                    before.add(one);
                    before.add(two);
                    // 버렸던 카드 중에서 한장과 현재 갖고 있는 카드를 합쳐서 낼 수 있는 경우 (코인 1개 소모)
                    for (final Integer c : before) {
                        if (need.contains(c)) {
                            coin--;
                            need.remove(c);
                            before.remove(c);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                    // 코인 2개를 사용해야 하는 경우
                    if (coin >= 2) {
                        // 첫번째 카드와 두번째 카드를 합쳐서 낼 수 있는 경우
                        if (one + two == toBe) {
                            coin -= 2;
                            before.remove(one);
                            before.remove(two);
                            continue;
                        }
                        // 첫번째 카드와 두번째 카드와 버렸던 카드 중에 두장을 합쳐서 낼 수 있는 경우
                        for (final Integer c : before) {
                            if (before.contains(toBe - c)) {
                                coin -= 2;
                                before.remove(c);
                                before.remove(toBe - c);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return round;
    }
}
