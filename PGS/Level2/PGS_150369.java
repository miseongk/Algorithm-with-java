package PGS.Level2;

import java.util.Stack;

public class PGS_150369 {

    public long solution(final int cap, final int n, final int[] deliveries, final int[] pickups) {
        long answer = 0;
        final Stack<int[]> d = new Stack<>();
        final Stack<int[]> p = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                d.add(new int[]{i + 1, deliveries[i]});
            }
            if (pickups[i] > 0) {
                p.add(new int[]{i + 1, pickups[i]});
            }
        }
        int dCnt = cap;
        int pCnt = cap;
        int[] dHouse;
        int[] pHouse;
        while (!d.isEmpty() && !p.isEmpty()) {
            dHouse = d.peek();
            pHouse = p.peek();
            if (dHouse[0] >= pHouse[0]) {
                answer += dHouse[0] * 2;
            } else {
                answer += pHouse[0] * 2;
            }
            while (dCnt > 0 && !d.isEmpty()) {
                dHouse = d.pop();
                dCnt = deliverOrPick(dHouse[0], dHouse[1], dCnt, d);
            }
            while (pCnt > 0 && !p.isEmpty()) {
                pHouse = p.pop();
                pCnt = deliverOrPick(pHouse[0], pHouse[1], pCnt, p);
            }
            dCnt = cap;
            pCnt = cap;
        }
        while (!d.isEmpty()) {
            dHouse = d.peek();
            answer += dHouse[0] * 2;
            while (dCnt > 0 && !d.isEmpty()) {
                dHouse = d.pop();
                dCnt = deliverOrPick(dHouse[0], dHouse[1], dCnt, d);
            }
            dCnt = cap;
        }
        while (!p.isEmpty()) {
            pHouse = p.peek();
            answer += pHouse[0] * 2;
            while (pCnt > 0 && !p.isEmpty()) {
                pHouse = p.pop();
                pCnt = deliverOrPick(pHouse[0], pHouse[1], pCnt, p);
            }
            pCnt = cap;
        }

        return answer;
    }

    private int deliverOrPick(final int dist, final int to, int cnt, final Stack<int[]> stack) {
        if (to > cnt) {
            stack.add(new int[]{dist, to - cnt});
            cnt = 0;
        } else {
            cnt -= to;
        }
        return cnt;
    }
}
