import java.util.Arrays;

public class BinaryHeap {
    private int[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this.heap = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getMin() throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException("Heap is empty");
        }
        return heap[0];
    }

    public void insert(int value) {
        if (size == heap.length) {
            resize();
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    private void heapifyUp(int index) {
        while (index > 0 && heap[index] < heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapifyDown(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }
}
