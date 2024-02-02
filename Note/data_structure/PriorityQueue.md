# 📓 PriorityQueue

- **우선순위가 높은 데이터가 먼저 나가는** 자료구조
- Heap 자료구조를 통해서 구현될 수 있음
- ⭐️ PriorityQueue를 사용하기 위해서는 저장할 객체가 **Comparable 인터페이스를 구현**해야 한다! ⭐️

## 우선순위 큐 사용 방법

### 큐 선언

- 낮은 숫자가 우선순위가 높은 int형 우선순위 큐 선언

```
final PriorityQueue<Integer> pq = new PriorityQueue<>();
```

- 높은 숫자가 우선순위가 높은 int형 우선순위 큐 선언

```
final PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

### 요소 삽입

```
pq.add(1);
// 또는
pq.offer(2);
```

### 요소 삭제

- `poll()`: 첫 번째 값을 반환하고 제거, 첫 번째 값이 없으면 null 반환

```
pq.poll();
```

- `remove()`: 첫 번째 값을 반환하고 제거, 첫 번째 값이 없으면 예외 발생

```
pq.remove();
```

- `clear()`: 큐 초기화

```
pq.clear();
```

### 요소 조회

- `peek()`: 첫 번째 값을 반환만 하고 제거하지 않음, 비어있다면 null 반환

```
pq.peek();
```

- `element()`: 첫 번째 값을 반환만 하고 제거하지 않음, 비어있다면 예외 발생

```
pq.element();
```

## 사용자 정의 객체로 우선순위 큐 사용하기

```java
public class PriorityQueueTest {

    public static void main(final String[] args) {
        final PriorityQueue<Student> pq = new PriorityQueue<>();

        pq.add(new Student("A", 90));
        pq.add(new Student("B", 95));
        pq.add(new Student("C", 100));

        while (!pq.isEmpty()) {
            final Student student = pq.poll();
            System.out.print(student);
        }
        // [name=A, score=90][name=B, score=95][name=C, score=100]
    }
}

class Student implements Comparable<Student> {

    private final String name;
    private final int score;

    public Student(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(final Student student) {
        if (this.score > student.score) {
            return 1;
        } else if (this.score < student.score) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", score=" + score + "]";
    }
}
```
