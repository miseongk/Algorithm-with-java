package Softeer.Level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SFTR_6277 {

    static List<List<int[]>> things = new ArrayList<>();
    static int K;
    static int answer = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);
        for (int i = 0; i <= K; i++) {
            things.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            String[] xyk = br.readLine().split(" ");
            int x = Integer.parseInt(xyk[0]);
            int y = Integer.parseInt(xyk[1]);
            int k = Integer.parseInt(xyk[2]);
            things.get(k).add(new int[]{x, y});
        }
        if (K == 1) {
            System.out.println(0);
            return;
        }
        int xMin = 1001;
        int yMin = 1001;
        int xMax = -1001;
        int yMax = -1001;
        findMin(1, xMin, yMin, xMax, yMax);
        System.out.println(answer);
    }

    private static void findMin(int depth, int xMin, int yMin, int xMax, int yMax) {
        if (depth == K + 1) {
            answer = Math.min((xMax - xMin) * (yMax - yMin), answer);
            return;
        }
        for (int i = 0; i < things.get(depth).size(); i++) {
            int xMinTmp = Math.min(things.get(depth).get(i)[0], xMin);
            int yMinTmp = Math.min(things.get(depth).get(i)[1], yMin);
            int xMaxTmp = Math.max(things.get(depth).get(i)[0], xMax);
            int yMaxTmp = Math.max(things.get(depth).get(i)[1], yMax);
            int tmp = (xMaxTmp - xMinTmp) * (yMaxTmp - yMinTmp);
            if (tmp < answer) {
                findMin(depth + 1, xMinTmp, yMinTmp, xMaxTmp, yMaxTmp);
            }
        }
    }
}

