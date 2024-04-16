# 📓 QuickSort - 퀵 정렬

## 정렬 과정

1. 배열 중에서 기준이 되는 원소를 하나 고른다. 고른 원소를 피벗(pivot) 이라고 한다.
2. 피벗을 기준으로 앞에는 피벗보다 값이 작은 모든 원소들이 오고, 피벗 뒤에는 피벗보다 큰 모든 원소들이 오도록 배열을 둘로 나눈다. 이렇게 배열을 둘로 나누는 것을 분할(Divide) 라고 한다. 분할을 마친
   뒤에 피벗은 더 이상 움직이지 않는다.
3. 분할된 두 개의 작은 배열에 대해 재귀적으로 위 과정을 반복한다.

## Java code

```java
public class QuickSort {

    public static void main(final String[] args) {
        final int[] array = {10, 7, 8, 9, 1, 5};

        quickSort(array, 0, array.length - 1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static void quickSort(final int[] array, final int left, final int right) {
        if (left < right) {
            final int pivot = partition(array, left, right);

            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    private static int partition(final int[] array, final int left, final int right) {
        final int pivot = array[right]; // 피벗을 배열의 마지막 요소로 설정
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private static void swap(final int[] array, final int i, final int j) {
        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
```

## 시간복잡도

### 최선의 경우 : O(NlogN)

- 피벗이 매번 배열을 중앙값 혹은 중앙에 가까운 값으로 선택될 경우
- 배열이 균등하게 두 부분으로 나눠지고, 재귀적으로 정렬하는 과정이 로그 깊이까지 반복, 각 레벨에서 n개의 작업이 수행 -> O(N * logN)

### 최악의 경우 : O(n^2)

- 피벗이 매번 최소값 또는 최대값으로 선택될 경우
- 이미 정렬된 배열에 대해 최소값 또는 최대값을 피벗으로 선택하면, 퀵 정렬은 한쪽은 빈 부분 배열, 다른 한쪽은 n - 1 크기의 부분 배열로 분할됨 -> 재귀의 깊이가 n까지 증가하며, 각 단계에서 n번의 비교
