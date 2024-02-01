# 📓 ArrayList

## 정렬하기

### 1. Collections.sort()

```java
public class ArrayListSort {

    public static void main(final String[] args) {
        final List<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(2);

        // 오름차순 정렬
        Collections.sort(arrayList);
        System.out.println(arrayList); // [1, 2, 3, 4]

        // 내림차순 정렬
        Collections.sort(arrayList, Collections.reverseOrder());
        System.out.println(arrayList); // [4, 3, 2, 1]
    }
}
```

### 2. List.sort()

```java
public class ArrayListSort {

    public static void main(final String[] args) {
        final List<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(2);

        // 오름차순 정렬
        arrayList.sort(Comparator.naturalOrder());
        System.out.println(arrayList); // [1, 2, 3, 4]

        // 내림차순 정렬
        arrayList.sort(Comparator.reverseOrder());
        System.out.println(arrayList); // [4, 3, 2, 1]
    }
}
```

### 3. 사용자 정의 객체 - Comparable, Comparator 인터페이스 구현

- Comparable
    - Comparable을 구현한 객체는 Collections.sort()로 정렬할 수 있음.

    ```java
    public class ArrayListSort {
    
        public static void main(final String[] args) {
            final List<Student> arrayList = new ArrayList<>();
    
            arrayList.add(new Student("A", 80));
            arrayList.add(new Student("B", 100));
            arrayList.add(new Student("C", 90));
    
            // 오름차순 정렬 (score 기준)
            Collections.sort(arrayList);
            System.out.println(arrayList); // [[ A: 80 ], [ C: 90 ], [ B: 100 ]]
    
    	// 내림차순 정렬 (score 기준)
            Collections.sort(arrayList, Collections.reverseOrder());
            System.out.println(arrayList); // [[ B: 100 ], [ C: 90 ], [ A: 80 ]]
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
            return "[ " + this.name + ": " + this.score + " ]";
        }
    }
    ```

- Comparator
    - Comparator를 만들어 Collections.sort()의 인자로 넣어주어 정렬할 수 있음

    ```java
    public class ArrayListSort {
    
        public static void main(final String[] args) {
            final List<Student> arrayList = new ArrayList<>();
    
            arrayList.add(new Student("A", 80));
            arrayList.add(new Student("B", 100));
            arrayList.add(new Student("C", 90));
            
            // 오름차순 정렬 (score 기준)
            Collections.sort(arrayList, new StudentComparator());
            System.out.println(arrayList); // [[ A: 80 ], [ C: 90 ], [ B: 100 ]]
  
    	// 내림차순 정렬 (score 기준)
            Collections.sort(arrayList, new StudentComparator().reversed());
            System.out.println(arrayList); // [[ B: 100 ], [ C: 90 ], [ A: 80 ]]
        }
    }
    
    class Student {
    
        final String name;
        final int score;
    
        public Student(final String name, final int score) {
            this.name = name;
            this.score = score;
        }
    
        @Override
        public String toString() {
            return "[ " + this.name + ": " + this.score + " ]";
        }
    }
    
    class StudentComparator implements Comparator<Student> {
    
        @Override
        public int compare(final Student student1, final Student student2) {
            if (student1.score > student2.score) {
                return 1;
            } else if (student1.score < student2.score) {
                return -1;
            }
            return 0;
        }
    }
    ```
