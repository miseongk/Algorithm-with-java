# 📓 HashMap

## 정렬하기

### 1. Key 값으로 정렬하기

```java
public class HashMapSort {

    public static void main(final String[] args) {
        final Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("B", 1);
        hashMap.put("C", 3);
        hashMap.put("A", 2);

        final List<String> keyList = new ArrayList<>(hashMap.keySet());

        // 오름차순 정렬 (key 기준)
        Collections.sort(keyList);
        for (final String key : keyList) {
            System.out.print(key + "=" + hashMap.get(key) + ", "); // A=2, B=1, C=3
        }

        // 내림차순 정렬 (key 기준)
        Collections.sort(keyList, Collections.reverseOrder());
        for (final String key : keyList) {
            System.out.print(key + "=" + hashMap.get(key) + ", "); // C=3, B=1, A=2
        }
    }
}
```

### 2. Value 값으로 정렬하기

```java
public class HashMapSort {

    public static void main(final String[] args) {
        final Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("B", 1);
        hashMap.put("C", 3);
        hashMap.put("A", 2);

        final List<String> keyList = new ArrayList<>(hashMap.keySet());

        // 오름차순 정렬 (value 기준)
        Collections.sort(keyList, new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                return hashMap.get(o1).compareTo(hashMap.get(o2));
            }
        });
        for (final String key : keyList) {
            System.out.print(key + "=" + hashMap.get(key) + ", "); // B=1, A=2, C=3
        }

        // 내림차순 정렬 (value 기준)
        Collections.sort(keyList, (o1, o2) -> hashMap.get(o2).compareTo(hashMap.get(o1)));
        for (final String key : keyList) {
            System.out.print(key + "=" + hashMap.get(key) + ", "); // C=3, A=2, B=1
        }
    }
}
```

## 메소드

### entrySet()

map 전체를 순회하면서 key와 value가 필요한 경우 사용

```java
public class HashMapMethod {

    public static void main(final String[] args) {
        final Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("B", 1);
        hashMap.put("C", 3);
        hashMap.put("A", 2);

        for (final Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + ", "); // A=2, B=1, C=3
        }
    }
}
```

- 출력값이 순서를 보장하지 않음 (순서 보장하려면 `LinkedHashMap` 사용)

### keySet()

key값이 필요한 경우 사용

```java
public class HashMapMethod {

    public static void main(final String[] args) {
        final Map<String, Integer> hashMap = new TreeMap<>();

        hashMap.put("B", 1);
        hashMap.put("C", 3);
        hashMap.put("A", 2);

        final Set<String> keySet = hashMap.keySet();
        for (final String key : keySet) {
            System.out.print(key + "=" + hashMap.get(key) + ", "); // A=2, B=1, C=3 
        }
    }
}
```
