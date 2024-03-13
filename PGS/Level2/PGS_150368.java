package PGS.Level2;

public class PGS_150368 {

    int[] rate = {40, 30, 20, 10};
    int[] arr;
    int maxPlus = 0;
    int totalPrice = 0;

    public int[] solution(final int[][] users, final int[] emoticons) {
        final int[] answer = {};
        arr = new int[emoticons.length];
        combination(0, users, emoticons);

        return new int[]{maxPlus, totalPrice};
    }

    private void combination(final int depth, final int[][] users, final int[] emoticons) {
        if (depth == emoticons.length) {
            findBest(users, emoticons);
            return;
        }

        for (int i = 0; i < 4; i++) {
            arr[depth] = rate[i];
            combination(depth + 1, users, emoticons);
        }
    }

    private void findBest(final int[][] users, final int[] emoticons) {
        int plus = 0;
        int allPrice = 0;
        for (int i = 0; i < users.length; i++) {
            int price = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (arr[j] >= users[i][0]) {
                    price += emoticons[j] * (1 - arr[j] * 0.01);
                    if (price >= users[i][1]) {
                        price = 0;
                        plus++;
                        break;
                    }
                }
            }
            allPrice += price;
        }
        if (plus > maxPlus) {
            maxPlus = plus;
            totalPrice = allPrice;
        } else if (plus == maxPlus) {
            totalPrice = Math.max(allPrice, totalPrice);
        }
    }
}
