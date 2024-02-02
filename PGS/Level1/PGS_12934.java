package PGS.Level1;

public class PGS_12934 {

    // 제곱근인지 판별할 때, 루트 연산 후 그 값이 정수이면 제곱근
    public long solution(final long n) {
        final double sqrt = Math.sqrt(n);
        if (sqrt % 1 == 0.0) {
            final long sqrtLong = (long) sqrt;
            return (sqrtLong + 1) * (sqrtLong + 1);
        }
        return -1;
    }
}
