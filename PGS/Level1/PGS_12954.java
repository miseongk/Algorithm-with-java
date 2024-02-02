package PGS.Level1;

public class PGS_12954 {

    public long[] solution(final int x, final int n) {
        final long[] answer = new long[n];

        // x의 범위가 -10000000 이상, 10000000 이하이기 때문에 (x * i) 연산 도중에 오버플로우 발생
        // int 를 long 으로 캐스팅 후 연산 전체가 long 범위에서 수행되도록 해야함!!
        for (int i = 0; i < n; i++) {
            final long add = i * (long) x;
            answer[i] = x + add;
        }

        return answer;
    }
}
