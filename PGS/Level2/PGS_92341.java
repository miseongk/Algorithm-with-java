package PGS.Level2;

import java.util.Map;
import java.util.TreeMap;

public class PGS_92341 {

    public int[] solution(final int[] fees, final String[] records) {
        final int[] answer;

        // 차량번호, [입차시각, 출차여부, 누적시간]
        final Map<String, String[]> cars = new TreeMap<>();
        for (int i = 0; i < records.length; i++) {
            final String[] split = records[i].split(" ");
            final String time = split[0];
            final String num = split[1];
            final String what = split[2];
            if (what.equals("IN")) {
                final String[] record = cars.getOrDefault(num, new String[]{"", "O", "0"});
                record[0] = time;
                record[1] = "X";
                cars.put(num, record);
            } else {
                final String[] record = cars.get(num);
                record[1] = "O";
                record[2] = String.valueOf(Integer.parseInt(record[2]) + calculateParkTime(record[0], time));
                cars.put(num, record);
            }
        }
        answer = new int[cars.size()];
        int i = 0;
        for (final String key : cars.keySet()) {
            final String[] record = cars.get(key);
            if (record[1].equals("X")) {
                record[2] = String.valueOf(Integer.parseInt(record[2]) + calculateParkTime(record[0], "23:59"));
            }
            answer[i] = calculateFee(Integer.parseInt(record[2]), fees);
            i++;
        }

        return answer;
    }

    private int calculateParkTime(final String in, final String out) {
        final int inMin = convertMinute(in);
        final int outMin = convertMinute(out);
        return outMin - inMin;
    }

    private int convertMinute(final String time) {
        final String[] split = time.split(":");
        final int hour = Integer.parseInt(split[0]);
        final int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    private int calculateFee(final int parkTime, final int[] fees) {
        int fee = fees[1];
        if (parkTime <= fees[0]) {
            return fee;
        }
        fee += Math.ceil((parkTime - fees[0]) / (fees[2] / 1.0)) * fees[3];
        return fee;
    }
}
