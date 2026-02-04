package sort;

import java.util.*;

public class SortingAlgorithms {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap /= 2;
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid ,right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void heapSortSmall(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifySmall(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapifySmall(arr, i, 0);
        }
    }

    private static void heapifySmall(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapifySmall(arr, n, largest);
        }
    }

    public static void heapSortBig(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyBig(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapifyBig(arr, i, 0);
        }
    }

    private static void heapifyBig(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }
        if (smallest != i) {
            swap(arr, smallest, i);
            heapifyBig(arr, n, smallest);
        }
    }

    public static void countingSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int num : arr) {
            count[num - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void bucketSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        int max = arr[0], min = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int bucketCount = 5;
        int bucketRange = (max - min) / bucketCount + 1;
        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : arr) {
            int bucketIndex = (num - min) / bucketRange;
            buckets.get(bucketIndex).add(num);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }
    public static void radixSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }
    public static void main(String[] args) {
        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 5, 77, 30, 11, 12, 542, 754, 124, 753, 123, 765};
        int[] original = testArray.clone();

        System.out.println("原始数组: " + Arrays.toString(original));

        // 测试各种排序算法
        testSort("冒泡排序", testArray.clone(), SortingAlgorithms::bubbleSort);
        testSort("选择排序", testArray.clone(), SortingAlgorithms::selectionSort);
        testSort("插入排序", testArray.clone(), SortingAlgorithms::insertionSort);
        testSort("希尔排序", testArray.clone(), SortingAlgorithms::shellSort);
        testSort("归并排序", testArray.clone(), SortingAlgorithms::mergeSort);
        testSort("快速排序", testArray.clone(), SortingAlgorithms::quickSort);
        testSort("da堆排序", testArray.clone(), SortingAlgorithms::heapSortBig);
        testSort("xiao堆排序", testArray.clone(), SortingAlgorithms::heapSortSmall);
        testSort("计数排序", testArray.clone(), SortingAlgorithms::countingSort);
        testSort("桶排序", testArray.clone(), SortingAlgorithms::bucketSort);
        testSort("基数排序", testArray.clone(), SortingAlgorithms::radixSort);
    }

    private static void testSort(String sortName, int[] arr, Consumer<int[]> sorter) {
        long startTime = System.nanoTime();
        sorter.accept(arr);
        long endTime = System.nanoTime();

        System.out.printf("%s: %s (耗时: %.3f ms)%n",
                sortName, Arrays.toString(arr), (endTime - startTime) / 1_000_000.0);
    }
    @FunctionalInterface
    interface Consumer<T> {
        void accept(T t);
    }
}
